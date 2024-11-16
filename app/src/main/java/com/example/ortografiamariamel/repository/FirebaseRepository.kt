package com.example.ortografiamariamel.repository

import android.content.Context
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


}
