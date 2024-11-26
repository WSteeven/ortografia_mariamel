// mi código para el juego 3 de la unidad 2
package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoGanador
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoPerdedor
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay

@Composable
fun CompletarParrafo(onPrevButtonClicked: () -> Unit = {}) {
    val firebase = FirebaseRepository(LocalContext.current)
    val words = remember {
        mutableStateListOf(
            "crecimiento",
            "nacimiento",
            "conocimiento",
            "establecimiento",
            "sufrimiento",
            "entendimiento"
        )
    }
    // Párrafo con los espacios marcados como _______
    val originalText = remember {
        "El _______ personal es un proceso continuo que se manifiesta a lo largo de la vida. " +
                "Desde el _______, cada experiencia nos brinda _______ que nos ayuda a enfrentar " +
                "los retos diarios. La educación juega un papel fundamental en el _______ de un buen futuro. " +
                "Sin embargo, no todo es fácil; el _______ y las dificultades también forman parte del camino " +
                "hacia el _______ de uno mismo."
    }

    val paragraph = remember {
        mutableStateOf(
            "El crecimiento personal es un proceso continuo que se manifiesta a lo largo de la vida. " +
                    "Desde el nacimiento, cada experiencia nos brinda conocimiento que nos ayuda a enfrentar " +
                    "los retos diarios. La educación juega un papel fundamental en el establecimiento de un buen futuro. " +
                    "Sin embargo, no todo es fácil; el sufrimiento y las dificultades también forman parte del camino " +
                    "hacia el entendimiento de uno mismo."
        )
    }


    var shuffledWords = remember { words.shuffled().toMutableStateList()}
    var currentText by remember { mutableStateOf(originalText) } // El texto actualizado
    val selectedWord by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableIntStateOf(-1) }
    var timer by remember { mutableIntStateOf(60) } // Tiempo en segundos
    var isGameOver by remember { mutableStateOf(false) }

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }

    // Función para reemplazar el primer marcador _______ con la palabra seleccionada
    fun replacePlaceholderWithWord(word: String) {
        // Reemplazar el primer "_______" con la palabra seleccionada
        currentText = currentText.replaceFirst("_______", word)
    }

    if (!isGameOver) {
        if (timer > 0) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                // Mostrar el tiempo
                Text(
                    "Tiempo restante: $timer segundos",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(16.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            border = BorderStroke(color = Color.Gray, width = 1.dp),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
//                    items(words.size) { index ->
                    items(shuffledWords.size) { index ->
                        val word = shuffledWords[index]
//                        val word = words[index]
                        ClickableText(
                            text = AnnotatedString(word),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .padding(8.dp),
                            onClick = {
                                replacePlaceholderWithWord(word)
                                shuffledWords.remove(word)
//                                words.remove(word)
                            }
                        )
                    }
                }
                // Mostrar el párrafo con espacios en blanco
                Text(
                    currentText,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(8.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Mostrar cuál palabra se ha seleccionado
                if (selectedWord != null) {
                    Text(
                        "Seleccionaste: ${selectedWord ?: ""}",
                        color = Color.Green,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isCorrect == 1)
                    Text(
                        "Felicidades, lo has hecho muy bien",
                        color = Color.Green,
                        fontSize = 18.sp
                    )
                if (isCorrect == 0)
                    Text("Tus respuestas estan mal", color = Color.Red, fontSize = 18.sp)

                // Botón para continuar o verificar la respuesta
                Button(
                    onClick = {
                        // Lógica de verificación del párrafo completo.
                        isCorrect = if (paragraph.value == currentText) {
                            1
                        } else 0
                        if (isCorrect == 1) firebase.actualizarProgreso(2,3, 6)
                        isGameOver = true
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Verificar")
                }
            }
        } else {
            FinJuegoPerdedor(
                modifier = Modifier,
                onRestartButtonCliked = {
                    isGameOver = false
                    timer = 60
                    isCorrect=-1
                    shuffledWords.clear() // Limpiar la lista
                    shuffledWords.addAll(words.shuffled())
                    currentText = originalText
                },
                onNextButtonClicked = onPrevButtonClicked
            )
        }
    } else {
        if (isCorrect == 1) {
            FinJuegoGanador(
                6, 6, onClick = onPrevButtonClicked
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "¡Oh no! No lo has hecho bien \uD83D\uDE25.",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp,
                    color = Color(230, 170, 75),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Text("Tus respuestas estan mal. !Buena suerte la próxima vez!", color = Color.Red, fontSize = 18.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                    verticalAlignment = Alignment.Bottom
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            isGameOver = false
                            timer = 60
                            isCorrect = -1
                            shuffledWords.clear() // Limpiar la lista
                            shuffledWords.addAll(words.shuffled())
                            currentText = originalText
                        }
                    ) {
                        Text(stringResource(R.string.reintentar))
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        // the button is enabled when the user makes a selection
                        onClick = {
                            onPrevButtonClicked()
                        }
                    ) {
                        Text(stringResource(R.string.back_to_menu_game))
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompletarParrafoPreview() {
    OrtografiaMariamelTheme {
        CompletarParrafo()
    }
}
