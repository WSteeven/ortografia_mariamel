package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay


val oracionesOriginales = listOf(
    "Te espero para tomar el té.",
    "Dice que el libro es de él",
    "Tú eres mi mejor amigo",
    "Ya sé que no te gusta el té",
    "Él me lo contó en la clase",
    "Tú tienes siempre la razón"
)

@Composable
fun SentencePuzzleGame() {
    var solvedCount by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }

    LaunchedEffect(solvedCount) {
        if (solvedCount == oracionesOriginales.size) {
            isGameOver = true
        }
    }
    var timer by remember { mutableIntStateOf(60) } // Tiempo en segundos

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }
    if (!isGameOver) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Respuestas correctas $solvedCount/${oracionesOriginales.size}",
                fontSize = 10.sp,
                textAlign = TextAlign.Right,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            // Mostrar el tiempo
            Text(
                "Tiempo restante: $timer segundos",
                fontSize = 10.sp,
                modifier = Modifier.padding(16.dp)
            )
            // Mostrar las palabras arrastrables
            oracionesOriginales.forEachIndexed { _, oracion ->
                GameScreen(oracion) { solved -> if (solved) solvedCount += 1 }
            }
        }

        if (timer<1) {
            // Botón de "Reiniciar"
            Text("¡Oh no! Se te acabó el tiempo.", color = Color.Green)
            Button(onClick = {
                solvedCount = 0
                isGameOver=false
                timer = 60
            }) {
                Text("Reiniciar")
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("¡Juego terminado! Has resuelto todas las oraciones.", color = Color.Green)
            // Botón de "Reiniciar"
            Button(onClick = {
                solvedCount=0
                isGameOver=false
                timer = 60
            }) {
                Text("Reiniciar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SentencePuzzleGamePreview() {
    OrtografiaMariamelTheme {
        SentencePuzzleGame()
    }
}
