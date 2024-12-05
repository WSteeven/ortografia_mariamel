package com.example.ortografiamariamel.ui.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.repository.User
import com.example.ortografiamariamel.repository.usersList
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import java.util.Locale

private const val ADMIN_PASSWORD = "admin123"

@Composable
fun AdminLoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: AppViewModel,
    onItemMenuButtonClicked: () -> Unit
) {
    var password by remember { mutableStateOf("") }

    MenuLateral(
        viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked,
        title = R.string.blank,
    ) {
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

}

@Composable
fun AdminScreen(
    viewModel: AppViewModel,
    onItemMenuButtonClicked: () -> Unit,
    navController: NavController
) {
    val firebase = FirebaseRepository(LocalContext.current)
    LaunchedEffect(Unit) {
        firebase.leerNombresFromFirebase()
    }
    MenuLateral(
        viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked,
        title = R.string.blank,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Muestra la lista de usuarios
            Text(text = "Lista de usuarios de la aplicación", fontWeight = FontWeight.Bold)
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(usersList) { user ->
                    UserCard(user = user) {
                        navController.navigate("user_details/${user.id}")
                    }
                }
            }

        }
    }
}

@Composable
fun UserCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable(onClick = onClick), elevation = CardDefaults.cardElevation(4.dp)
    ) {
        fun dividirTextoYNumero(cadena: String): Pair<String, Int> {
            // Utilizar expresión regular para separar el texto de los números al final
            val regex =
                "(\\D+)(\\d+)$".toRegex()  // Busca texto seguido de un número al final de la cadena
            val resultado = regex.find(cadena)

            // Si encuentra un resultado, devuelve el texto y el número
            return if (resultado != null) {
                val texto = resultado.groupValues[1]  // Texto antes del número
                val numero = resultado.groupValues[2].toInt()  // Número al final de la cadena
                Pair(texto.trim(), numero)  // Devolver el par (texto, número)
            } else {
                Pair(
                    cadena,
                    0
                )  // Si no encuentra un número, devuelve la cadena completa y 0 como número
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Imágenes de perfil de usuario o cualquier otro dato que quieras mostrar
            Text(text = user.nombre, fontWeight = FontWeight.Bold)
            if (user.unidad != "")
                Text(
                    text = "${dividirTextoYNumero(user.unidad).first.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }}:  ${dividirTextoYNumero(user.unidad).second}",
                    fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic
                )
            else
                Text(text = "No empezado")
            if (user.juego != "")
                Text(
                    text = "${dividirTextoYNumero(user.juego).first.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }}:  ${dividirTextoYNumero(user.juego).second}",
                    fontWeight = FontWeight.SemiBold,
                )
            else
                Text(text = "Aún sin jugar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    OrtografiaMariamelTheme {
        UserCard(user = User(nombre = "Steeven", unidad = "unidad3", juego = "juego5")) {}
    }
}

@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    OrtografiaMariamelTheme {
        AdminScreen(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onItemMenuButtonClicked = {}, navController = NavController(LocalContext.current)
        )
    }
}