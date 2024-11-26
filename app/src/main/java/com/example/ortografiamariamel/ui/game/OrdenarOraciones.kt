package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoGanador
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoPerdedor
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
fun SentencePuzzleGame(onPrevButtonClicked: () -> Unit = {}) {
    val firebase = FirebaseRepository(LocalContext.current)
    var solvedCount by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(60) } // Tiempo en segundos

    LaunchedEffect(solvedCount) {
        if (solvedCount == oracionesOriginales.size) {
            isGameOver = true
        }
    }

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }
    if (isGameOver) {
        firebase.actualizarProgreso(1, 2, solvedCount)
        FinJuegoGanador(
            respuestasCorrectas = solvedCount,
            totalRespuestas = oracionesOriginales.size, onClick = onPrevButtonClicked
        )
    } else {
        if (timer > 0) {
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

        } else {
            FinJuegoPerdedor(onRestartButtonCliked = {
                solvedCount = 0
                isGameOver = false
                timer = 60
            }, onNextButtonClicked = onPrevButtonClicked)
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
