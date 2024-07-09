package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.data.Carta
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun MatchPairs() {
    var definiciones = remember {
        mutableStateListOf(
            Carta(id = 1, texto = "Artículo", 1),
            Carta(id = 2, texto = "Pronombre Personal", 2),
            Carta(id = 3, texto = "Adjetivo", 3),
            Carta(id = 4, texto = "Sustantivo", 4),
        )
    }
    var respuestas = remember {
        mutableStateListOf(
            Carta(id = 1, texto = "El", 1),
            Carta(id = 2, texto = "Tú", 2),
            Carta(id = 3, texto = "Mi", 3),
            Carta(id = 4, texto = "Té", 4),
        )
    }
    var selectedDefinicion: Carta? by remember { mutableStateOf(null) }

    Column {
        Row {
            ListaDefinificiones(cartas = definiciones, onCardClicked = { carta: Carta ->
                // Si ya hay una carta seleccionada, intenta hacer el match
                if (selectedDefinicion != null) {
                    // Verifica si es un match
                    if (selectedDefinicion!!.id != carta.id) {
                        // No es un match, deselecciona la carta anterior
                        definiciones.find { it.id == selectedDefinicion!!.id }?.isSelected = false
                    } else {
                        // Es un match, marca las cartas como matched
                        definiciones.find { it.id == selectedDefinicion!!.id }?.isMatched = true
                        respuestas.find { it.id == carta.id }?.isMatched = true
                    }
                    // Deselecciona la carta actualmente seleccionada
                    selectedDefinicion = null
                } else {
                    // No hay carta seleccionada, selecciona esta carta
                    selectedDefinicion = carta
                }
                // Actualiza el estado
                definiciones.find { it.id == carta.id }?.isSelected = true
            })
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp) // Espacio uniforme entre las cartas
        ) {
            ListaRespuestas(cartas = respuestas)
        }
    }
}

@Composable
fun ListaDefinificiones(cartas: List<Carta>, onCardClicked: (Carta) -> Unit = {}) {
    cartas.forEach { carta ->
        CardSimple(carta = carta, onClick = { onCardClicked(carta) })
    }
}

@Composable
fun CardSimple(carta: Carta, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(90.dp)
            .padding(8.dp),
        border = BorderStroke(1.dp, Color(255, 168, 0)),
        colors = CardDefaults.cardColors(containerColor = if (carta.isMatched) Color.Green else if (carta.isSelected) Color.Yellow else Color.LightGray),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(text = carta.texto, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ListaRespuestas(cartas: List<Carta>) {
    cartas.forEach { carta ->
        CardCarta(carta)
    }
}

@Composable
fun CardCarta(carta: Carta) {
    val imagen_carta: Int = R.drawable.carta1
    val imagen_carta2: Int = R.drawable.carta2
    var imageClicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .height(120.dp) // Tamaño fijo para cada carta
            .width(90.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = {
                    imageClicked = !imageClicked
                }),
            contentAlignment = Alignment.Center
        ) {
            if (imageClicked) {
                Text(carta.texto)
            } else {
                Image(painter = painterResource(imagen_carta), contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardSimplePreview() {
    OrtografiaMariamelTheme {
        CardSimple(Carta(id = 1, "Articulo", 1, false, false))
    }
}

@Preview(showBackground = true)
@Composable
fun CardCartaPreview() {
    OrtografiaMariamelTheme {
        CardCarta(Carta(id = 1, "tu", 1, true, false))
    }
}

@Preview(showBackground = true)
@Composable
fun ActividadScreenPreview() {
    OrtografiaMariamelTheme {
        MatchPairs()
    }
}
