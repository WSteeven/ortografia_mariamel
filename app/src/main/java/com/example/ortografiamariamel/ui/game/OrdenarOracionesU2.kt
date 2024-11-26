package com.example.ortografiamariamel.ui.game

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoGanador
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

val frasesOrdenadas = listOf(
    "El crecimiento de la planta se nota cada día.",
    "El fallecimiento de su amigo dejó un gran vacío.",
    "El movimiento fue rápido.",
    "Su pensamiento crítico le ayuda a resolver problemas.",
    "Hay un fortalecimiento en el equipo.",
    "Recibió un reconocimiento especial.",
)

@Composable
fun JuegoOrdenarOraciones(
    snackbarHostState: SnackbarHostState,
    onPrevButtonClicked: () -> Unit = {},
) {
    val firebase = FirebaseRepository(LocalContext.current)
    val shuffledFrases = remember { frasesOrdenadas.shuffled().toMutableStateList() }
    val userOrder = remember { mutableStateListOf<String>() }
    var solvedCount by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }
    var indice by remember { mutableIntStateOf(-1) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { snackbarHostState.showSnackbar(it)
        snackbarMessage = null}
    }

    LaunchedEffect(solvedCount) {
        if (solvedCount == palabras.size) {
            isGameOver = true
        }
    }

    if (isGameOver) {
        firebase.actualizarProgreso(2, 2, solvedCount)
        // Cuando el juego termina, muestra la pantalla de FinJuegoUnidad1
        FinJuegoGanador(
            respuestasCorrectas = solvedCount,
            totalRespuestas = palabras.size,
            onClick = onPrevButtonClicked
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(
                "Respuestas correctas $solvedCount/${palabras.size}",
                fontSize = 10.sp,
                textAlign = TextAlign.Right,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            shuffledFrases.forEachIndexed { index, frase ->
                indice = index
                OracionDesordenada(oracion = frase, onMessage ={ snackbarMessage=it}, onClick = {
                    Log.d("OrdenarOracionesU2", "Palabra clickeada $it")
                    userOrder.add(it)
                }) {
                    if (it) solvedCount += 1
                }

            }

        }
        Spacer(modifier = Modifier.height(4.dp))
    }

}

@Composable
fun OracionDesordenada(oracion: String, onMessage: (String)->Unit, onClick: (String) -> Unit, onSolved: (Boolean) -> Unit) {
    val originalWords = oracion.split(" ")
    val shuffledWords = remember { originalWords.shuffled().toMutableStateList() }
    val userOrder = remember { mutableStateListOf<String>() }
    var isSolved by remember { mutableStateOf(false) }

    if (!isSolved) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(
                        border = BorderStroke(color = Color.Gray, width = 1.dp),
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                items(shuffledWords.size) { index ->
                    val palabra = shuffledWords[index]
                    ClickableText(
                        text = AnnotatedString("${index + 1}. $palabra "),
                        modifier = Modifier
                            .border(
                                border = BorderStroke(color = Color.Gray, width = 1.dp),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp),
                        onClick = {
                            onClick(palabra)
                            // Primero desmarcamos todas las palabras
                            userOrder.add(palabra)
                            shuffledWords.remove(palabra)
                            // Actualiza el estado de la palabra según la posición
                            if (userOrder.toList() == originalWords) {
                                isSolved = true
                                onSolved(true)

                            }
//                        Log.d("EmparejarFrases","Di click ${palabra.isClicked}")
                        })
                }
            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .height(200.dp)
            ) {
                items(userOrder.size) { index ->
                    val word = userOrder[index]
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(1.dp, Color.Black), // Borde en la parte inferior
                                shape = CircleShape
                            )
//                        .padding(bottom = 10.dp)
                    ) {
                        ClickableText(text = AnnotatedString(word),
                            modifier = Modifier.background(Color.Yellow),
                            onClick = {
                                userOrder.remove(word)
                                shuffledWords.add(word)
                            }
                        )
                    }
                }
            }

            if (shuffledWords.size == 0) {
                Button(onClick = {
                    if (userOrder.toList() == originalWords) {
                        isSolved = true
                        onSolved(true)
                    }else{
                        onMessage("No está ordenado,corrige e intenta nuevamente")
                    }
                }) {
                    Text("Verificar")
                }
            }
        }
    } else {
        onMessage("Lo hiciste muy bien")
    }
}

@Preview(showBackground = true)
@Composable
fun JuegoOrdenarOracionesPreview() {
    OrtografiaMariamelTheme {
        JuegoOrdenarOraciones(snackbarHostState = SnackbarHostState())
    }
}

//@Preview(showBackground = true, heightDp = 200)
//@Composable
//fun OracionDesordenadaPreview() {
//    OrtografiaMariamelTheme {
//        OracionDesordenada("El crecimiento de la planta se nota cada día", {}) {}
//    }
//}
