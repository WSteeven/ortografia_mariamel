package com.example.ortografiamariamel.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun UserDetailsScreen(userId: String) {
    // Aquí puedes cargar y mostrar los detalles del usuario
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Detalles de Usuario", fontWeight = FontWeight.Bold)
        Text(text = "ID: $userId")

        // Puedes agregar más detalles sobre el progreso del usuario, por ejemplo
    }
}

@Composable
@Preview(showBackground = true)
fun UserDetailsScreenPreview(){
    OrtografiaMariamelTheme {
        UserDetailsScreen(userId = "12")
    }
}