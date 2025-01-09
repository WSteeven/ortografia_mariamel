package com.example.ortografiamariamel.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.repository.Juego
import com.example.ortografiamariamel.repository.Usuario
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun UserDetailsScreen(
    userId: String, viewModel: AppViewModel,
    onItemMenuButtonClicked: () -> Unit
) {
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    val firebase = FirebaseRepository(LocalContext.current)

    LaunchedEffect(userId) {
        firebase.obtenerDetallesUsuario(userId) { result -> usuario = result }
    }
    MenuLateral(viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked, title = R.string.blank) {

        // Aquí puedes cargar y mostrar los detalles del usuario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Detalles de Usuario", fontWeight = FontWeight.Bold)
            if (usuario != null) {
                Text("Nombre: ${usuario!!.nombre}")
                Text("Progreso: ")

                // Recorre las unidades y juegos
                usuario!!.progreso.forEach { (unidad, detallesUnidad) ->
                    Text(text = "Unidad: $unidad", fontWeight = FontWeight.Bold)

                    TableHeader()
                    detallesUnidad.juegos.forEach { (juego, detallesJuego) ->
//                    Text(text = "  Juego: $juego")
//                    Text(text = "    Puntaje: ${detallesJuego.puntaje}")
//                    Text(text = "    Completado: ${detallesJuego.completado}")
                        TableRow(juego, detallesJuego)
                    }
                }
            } else {
                Text("Cargando detalles...")
            }
//        Text(text = "ID: $userId")

            // Puedes agregar más detalles sobre el progreso del usuario, por ejemplo
        }
    }
}

@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Juego", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        Text(text = "Puntaje", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        Text(text = "Completado", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TableRow(juego: String, detallesJuego: Juego) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = juego, modifier = Modifier.weight(1f))
        Text(text = detallesJuego.puntaje.toString(), modifier = Modifier.weight(1f))
        Text(text = if (detallesJuego.completado) "Sí" else "No", modifier = Modifier.weight(1f))
    }
}


@Composable
@Preview(showBackground = true)
fun UserDetailsScreenPreview() {
    OrtografiaMariamelTheme {
        UserDetailsScreen(userId = "12", viewModel = viewModel(factory = AppViewModelProvider.Factory)){}
    }
}