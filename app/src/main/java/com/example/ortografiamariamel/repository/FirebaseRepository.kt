package com.example.ortografiamariamel.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

data class User(val id:String="", val nombre: String="")

val usersList = mutableStateListOf<User>()
class FirebaseRepository(private val context: Context) {

    fun getOrCreateUniqueId(): String {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        var uniqueId = sharedPreferences.getString("unique_id", null)

        if (uniqueId == null) {
            uniqueId = UUID.randomUUID().toString()
            sharedPreferences.edit().putString("unique_id", uniqueId).apply()
        }
        return uniqueId ?: ""
    }

    fun guardarNombreToFirebase(nombre: String) {
        val database = FirebaseDatabase.getInstance().reference
        val userId = getOrCreateUniqueId()
        val userData = mapOf("nombre" to nombre)

        database.child("users").child(userId).setValue(userData)
            .addOnSuccessListener {
                // Datos guardados con éxito
                guardarNombreLocalmente(nombre)
            }
            .addOnFailureListener { e ->
                // Manejo de errores
                Log.d("guardarNombreToFirebase","Hubo un error $e")
            }
    }

    fun leerNombresFromFirebase(){
        val database = FirebaseDatabase.getInstance().reference

        database.child("users").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                usersList.clear() // limpia la lista antes de llenarla
                for (user in snapshot.children){
                    val userId = user.key?:""
                    val nombre = user.child("nombre").getValue(String::class.java)?:""
                    usersList.add(User(id = userId, nombre=nombre))

                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores

            }
        })
    }
     fun guardarNombreLocalmente(nombre: String) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("nombre", nombre)
            apply()
        }
    }
    fun leerNombreLocalmente(): String?{
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("nombre", null)
    }

    fun actualizarProgreso(unidad: Int, juego:Int, puntaje:Int=0){
        val userId = getOrCreateUniqueId()
        val database = FirebaseDatabase.getInstance()
        val progresoRef = database.getReference("users/$userId/progreso")

        //Crear referencia a la unidad y el juego
        val juegoRef = progresoRef.child("unidad$unidad").child("juego$juego")

        // Actualizar el puntaje y estado de completado
        juegoRef.child("puntaje").setValue(puntaje)
        juegoRef.child("completado").setValue(true)

        //Desbloquear el siguiente juego de la misma unidad si corresponde
        if(juego<3){
            val siguienteJuegoRef = progresoRef.child("unidad$unidad").child("juego${juego+1}")
            siguienteJuegoRef.child("completado").setValue(false)
        }
        else if(unidad<4){
            val siguienteUnidadRef = progresoRef.child("unidad${unidad + 1}").child("juego1")
            siguienteUnidadRef.child("completado").setValue(false) // Habilitar el primer juego de la siguiente unidad
        }
    }

    fun verificarJuegoCompletado(unidad: Int, juego: Int, onResultado: (Boolean) -> Unit) {
        val userId = getOrCreateUniqueId()
        val database = FirebaseDatabase.getInstance()
        val progresoRef = database.getReference("users/$userId/progreso")

        progresoRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Accedemos al progreso de la unidad y el juego
                val unidadRef = dataSnapshot.child("unidad$unidad")
                val juegoRef = unidadRef.child("juego$juego")

                val completado = juegoRef.child("completado").getValue(Boolean::class.java) ?: false

                // Llamamos al callback con el resultado (si está completado o no)
                onResultado(completado)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores, si es necesario
            }
        })
    }

    fun cargarProgreso( onProgresoCargado: (Progreso)->Unit){
        val userId = getOrCreateUniqueId()
        val database = FirebaseDatabase.getInstance()
        val progresoRef = database.getReference("users/$userId/progreso")

        progresoRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val progreso = snapshot.getValue(Progreso::class.java)
                // Llamamos al callback con el progreso cargado
                if(progreso!=null){
                    onProgresoCargado(progreso)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun habilitarJuegosSegunProgreso(){
        cargarProgreso { progreso ->
            // Revisamos si el primer juego de la unidad 1 está completado, si es así, habilitamos el segundo juego
            if (progreso.unidad1?.juego1?.completado == true) {
                habilitarJuego("unidad1", 2)
            }
            if (progreso.unidad1?.juego2?.completado == true) {
                habilitarJuego("unidad1", 3)
            }
            if (progreso.unidad2?.juego1?.completado == true) {
                habilitarJuego("unidad2", 1)
            }
            // Y así sucesivamente para las demás unidades y juegos
        }
    }
    fun habilitarJuego(unidad: String, juego: Int) {
        // Aquí puedes habilitar el juego en la UI (mostrarlo o permitirlo jugar)
        Log.d("habilitarJuego","Habilitar $unidad - Juego $juego")
    }



}

data class Progreso(
    val unidad1: Unidad? = null,
    val unidad2: Unidad? = null,
    val unidad3: Unidad? = null,
    val unidad4: Unidad? = null
)

data class Unidad(
    val juego1: Juego? = null,
    val juego2: Juego? = null,
    val juego3: Juego? = null
)

data class Juego(
    val puntaje: Int = 0,
    val completado: Boolean = false
)
