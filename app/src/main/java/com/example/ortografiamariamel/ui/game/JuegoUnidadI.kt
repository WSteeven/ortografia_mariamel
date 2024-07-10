package com.example.ortografiamariamel.ui.game

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.data.Datasource
import com.example.ortografiamariamel.model.Carta
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.launch


@Composable
fun MatchPairs() {
    val datasource = Datasource()
    var definiciones by remember { mutableStateOf(datasource.loadDefiniciones()) }
    var respuestas by remember { mutableStateOf(datasource.loadRespuestas()) }
    var selectedRespuesta: Carta? by remember { mutableStateOf(null) }
    var selectedDefinicion: Carta? by remember { mutableStateOf(null) }
    var puntos by remember { mutableStateOf(0) }
    var intento by remember { mutableStateOf(0) }

    Column {
        Text("Paso 1. Escoge una carta")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp) // Espacio uniforme entre las cartas
        ) {
            ListaRespuestas(cartas = respuestas, onCardClicked = { carta ->
                respuestas = respuestas.map { it.copy(isSelected = it.id == carta.id) }
                definiciones = definiciones.map { it.copy(isSelected = false) }
                carta.isSelected = !carta.isSelected
                selectedDefinicion = null
                selectedRespuesta = carta
            })
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Text("Paso 2. Escoge la respuesta correcta")
        Row {
            ListaDefinificiones(cartas = definiciones, onCardClicked = { carta ->
                intento = 0
                // Al hacer clic en una carta de definiciones
                definiciones = definiciones.map { it.copy(isSelected = it.id == carta.id) }
                carta.isSelected = !carta.isSelected
                selectedDefinicion = carta
                Log.d("CardClicked", "Dentro del 80: ${selectedDefinicion} - ${selectedRespuesta}")
                // Comprobar si hay match
                if (selectedDefinicion!!.id == selectedRespuesta!!.id) {
                    respuestas =
                        respuestas.map { it.copy(isMatched = it.id == carta.id || it.isMatched) }
                    definiciones =
                        definiciones.map { it.copy(isMatched = it.id == carta.id || it.isMatched) }
                }
            })
        }
    }

}

@Composable
fun ListaDefinificiones(cartas: List<Carta>, onCardClicked: (Carta) -> Unit = {}) {
    cartas.forEach { carta ->
        CardSimple(
            carta = carta,
            onClick = { onCardClicked(carta) },
            elevation = if (carta.isSelected) 8.dp else 0.dp,
            backgroundColor = if (carta.isMatched) Color.Green else if (carta.isSelected) Color.Yellow else Color.LightGray
        )
    }
}

@Composable
fun CardSimple(
    carta: Carta,
    onClick: () -> Unit = {},
    elevation: Dp = 0.dp,
    backgroundColor: Color = Color.LightGray
) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(90.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clickable(onClick = {
//                    Log.d("CardClicked", "Carta definicion seleccionada: ${carta}")
                    onClick()
                }),
            contentAlignment = Alignment.Center
        ) {
            Text(text = carta.texto, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ListaRespuestas(cartas: List<Carta>, onCardClicked: (Carta) -> Unit = {}) {
    cartas.forEach { carta ->
        CardCarta(
            carta = carta,
            onClick = { onCardClicked(carta) },
            elevation = if (carta.isSelected) 8.dp else 0.dp,
            backgroundColor = if (carta.isMatched) Color.Green else if (carta.isSelected) Color.Yellow else Color.LightGray
        )
    }
}

@Composable
fun CardCarta(
    carta: Carta,
    onClick: () -> Unit = {},
    elevation: Dp = 0.dp,
    backgroundColor: Color = Color.LightGray
) {
    val imagenCarta: Int = R.drawable.carta1

    Card(
        modifier = Modifier
            .height(120.dp) // Tamaño fijo para cada carta
            .width(90.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    onClick = {
                        Log.d("CardClicked", "Carta negra seleccionada: ${carta}")
                        onClick()
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (carta.isSelected || carta.isMatched) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(carta.texto)
                }

            } else {
                Image(painter = painterResource(imagenCarta), contentDescription = null)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardSimplePreview() {
    OrtografiaMariamelTheme {
        CardSimple(Carta(id = 1, "Articulo", isSelected = false, isMatched = false))
    }
}

@Preview(showBackground = true)
@Composable
fun CardCartaPreview() {
    OrtografiaMariamelTheme {
        CardCarta(Carta(id = 1, "tu", isSelected = false, isMatched = false))
    }
}

@Preview(showBackground = true)
@Composable
fun ActividadScreenPreview() {
    OrtografiaMariamelTheme {
        MatchPairs()
    }
}
