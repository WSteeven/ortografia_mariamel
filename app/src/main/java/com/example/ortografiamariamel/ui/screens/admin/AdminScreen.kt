package com.example.ortografiamariamel.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.repository.User
import com.example.ortografiamariamel.repository.usersList

private const val ADMIN_PASSWORD = "admin123"

@Composable
fun AdminLoginScreen(onLoginSuccess: () -> Unit) {
    var password by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ingrese la contraseña de administrador")
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            if (password == ADMIN_PASSWORD) {
                onLoginSuccess()
            } else {
                // Mostrar un mensaje de error
            }
        }) {
            Text("Iniciar sesión")
        }
    }
}

@Composable
fun AdminScreen() {
    val firebase = FirebaseRepository(LocalContext.current)
    LaunchedEffect(Unit) {
        firebase.leerNombresFromFirebase()
    }
    Column(modifier = Modifier.fillMaxSize()) {

        Text("Esta es la pantalla de administración")
        Text(text = "Acá abajo aparecerá toda la información de la base de datos como usuarios con sus puntajes obtenidos y su progreso en la aplicación")
        Spacer(modifier = Modifier.padding(24.dp))
        // Muestra la lista de usuarios
        Text(text = "Lista de usuarios de la aplicación", fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(usersList) { user ->
                UserRow(user)
            }
        }
    }
}

@Composable
fun UserRow(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = user.nombre, modifier = Modifier.weight(1f))
        // Puedes agregar más información o botones aquí
    }
}