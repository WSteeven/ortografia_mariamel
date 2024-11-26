package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoGanador
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoPerdedor
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay

data class Palabra(val text: String, var isClicked: Boolean = false)

val palabras = listOf(
    Palabra("NACIMIENTO"),
    Palabra("CRECIMIENTO"),
    Palabra("AGRADECIMIENTO"),
    Palabra("CONOCIMIENTO"),
    Palabra("ACONTECIMIENTO")
)

data class Frase(val text: String, val palabraCorrecta: String, var isClicked: Boolean = false)

val frases = listOf(
    Frase("Proceso de nacer.", "NACIMIENTO"),
    Frase("Acto de construir o crecer.", "CRECIMIENTO"),
    Frase("Expresión de gratitud.", "AGRADECIMIENTO"),
    Frase("Proceso de conocer o hacerse conocido.", "CONOCIMIENTO"),
    Frase("Evento, hecho o suceso.", "ACONTECIMIENTO")
)

@Composable
fun EmparejarFrases(
    onPrevButtonClicked: () -> Unit = {},
) {
    val firebase = FirebaseRepository(LocalContext.current)
    var shuffledPalabras = remember { palabras.shuffled().toMutableStateList() }
    var shuffledFrases = remember { frases.shuffled().toMutableStateList() }
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
    var solvedCount by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }
    var selectedPalabra by remember { mutableStateOf("") }
    var selectedFrase by remember { mutableStateOf("") }
    var timer by remember { mutableIntStateOf(60) } // Tiempo en segundos

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }

    LaunchedEffect(solvedCount) {
        if (solvedCount == palabras.size) {
            isGameOver = true
        }
    }

    if (isGameOver) {
        firebase.actualizarProgreso(2,1, solvedCount)
        // Cuando el juego termina, muestra la pantalla de FinJuegoUnidad1
        FinJuegoGanador(
            respuestasCorrectas = solvedCount,
            totalRespuestas = palabras.size,
            onClick = onPrevButtonClicked
        )
    } else {
        if (timer > 0) {
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
                // Mostrar el tiempo
                Text(
                    "Tiempo restante: $timer segundos",
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    "* Desliza hacia la derecha para ver más palabras",
                    textAlign = TextAlign.End,
                    fontSize = 6.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .border(
                            border = BorderStroke(color = Color.Gray, width = 1.dp),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    items(shuffledPalabras.size) { index ->
                        val palabra = shuffledPalabras[index]
                        ClickableText(
                            text = AnnotatedString("${index + 1}. ${palabra.text} "),
                            modifier = Modifier
                                .background(color = if (palabra.isClicked) colors[index] else Color.Transparent)
                                .border(
                                    border = BorderStroke(color = colors[index], width = 1.dp),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(8.dp),
                            onClick = {
                                // Primero desmarcamos todas las palabras
                                shuffledPalabras.forEachIndexed { index, _ ->
                                    shuffledPalabras[index] =
                                        shuffledPalabras[index].copy(isClicked = false)
                                }
                                shuffledPalabras[index] =
                                    palabra.copy(isClicked = !palabra.isClicked)
                                selectedPalabra = palabra.text
//                        Log.d("EmparejarFrases","Di click ${palabra.isClicked}")
                            })
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .align(Alignment.End)
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
//                    .border(
//                        border = BorderStroke(color = Color.Gray, width = 1.dp),
//                        shape = RoundedCornerShape(4.dp)
//                    )

                    ) {
                        items(shuffledFrases.size) { index ->
                            val frase = shuffledFrases[index]

                            ClickableText(text = AnnotatedString("${index + 1}. ${frase.text}"),
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(8.dp),
                                onClick = {
                                    selectedFrase = frase.text
                                    // Verificamos si la palabra y frase seleccionadas coinciden
                                    if (selectedPalabra == frase.palabraCorrecta) {
                                        // Sumamos 1 a las respuestas correctas
                                        solvedCount += 1
                                        // Eliminamos de ambas listas las palabras seleccionadas

                                        // Eliminamos la palabra seleccionada de shuffledPalabras
                                        shuffledPalabras.removeAll { it.text == selectedPalabra }

                                        // Eliminamos la frase correcta de shuffledFrases
                                        shuffledFrases.removeAll { it.palabraCorrecta == selectedPalabra }
                                    }
                                })


                        }
                    }
                }
            }
        } else {
            FinJuegoPerdedor(
                modifier = Modifier,
                onRestartButtonCliked = {
                    solvedCount = 0
                    isGameOver = false
                    timer = 60
                    shuffledPalabras = palabras.shuffled().toMutableStateList()
                    shuffledFrases = frases.shuffled().toMutableStateList()
                    selectedPalabra = ""
                    selectedFrase = ""

                },
                onNextButtonClicked = onPrevButtonClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmparejarFrasePreview() {
    OrtografiaMariamelTheme {
        EmparejarFrases()
    }
}