package com.example.ortografiamariamel.ui.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

data class Sentence2U1(val id: Int, val words: List<String>)

val originalSentences = listOf(
//    Sentence2U1(1, listOf("Te", "espero", "para", "tomar", "el", "té")),
//    Sentence2U1(2, listOf("Dice", "que", "el", "libro", "es", "de", "él")),
    Sentence2U1(3, listOf("Tú", "eres", "mi", "mejor", "amigo")),
//    Sentence2U1(4, listOf("Ya", "sé", "que", "no", "te", "gusta", "el", "té")),
//    Sentence2U1(5, listOf("Él", "me", "lo", "contó", "en", "la", "clase")),
    Sentence2U1(6, listOf("Tú", "tienes", "siempre", "la", "razón"))
)

@Composable
fun SentencePuzzle(sentence: Sentence2U1, onSolved: (Boolean) -> Unit) {
    var words by remember { mutableStateOf(sentence.words.shuffled()) } // Las palabras desordenadas
    var timer by remember { mutableIntStateOf(60) } // Tiempo en segundos
    var isSolved by remember { mutableStateOf(false) }

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Mostrar las palabras arrastrables
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            words.forEachIndexed { index, word ->
                DraggableWord(word = word,
                    currentIndex=index,
                    onDragEnd = { newIndex ->
                    words = words.toMutableList().apply {
                        removeAt(index)
                        add(newIndex, word)
                    }
                })
            }
        }

        // Mostrar el tiempo
        Text(
            "Tiempo restante: $timer segundos",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Comprobar si la oración está resuelta
        if (!isSolved && words == sentence.words) {
            isSolved = true
            onSolved(true)
        }

        // Botón de "Reiniciar"
        Button(onClick = {
            words = sentence.words.shuffled()
            isSolved = false
        }) {
            Text("Reiniciar")
        }

        if (isSolved) {
            Text("¡Oración resuelta!", color = Color.Green, fontSize = 20.sp)
        }
    }
}

@Composable
fun DraggableWord(word: String, currentIndex: Int, onDragEnd: (Int) -> Unit) {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }  // Posición inicial
    var dragOffset by remember { mutableStateOf(Offset(0f, 0f)) }  // Desplazamiento mientras se arrastra

    Box(
        modifier = Modifier
            .padding(8.dp)
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }  // Posicionamiento de la palabra
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    // Al arrastrar la palabra, actualizamos su posición
                    dragOffset = Offset(dragOffset.x + dragAmount.x, dragOffset.y + dragAmount.y)

                    // Actualizamos la posición de la palabra mientras se arrastra
                    offset = Offset(dragOffset.x, dragOffset.y)
                }
            }
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
    ) {
        Text(word, fontSize = 6.sp, modifier = Modifier.padding(8.dp))
    }

    // Una vez que la palabra ha sido soltada, calculamos su nueva posición (índice)
    // Esto puede hacerse cuando finalice el drag o en otra parte del código que se te ocurra
    // Puedes llamar a `onDragEnd(newIndex)` para actualizar el estado del juego.
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SentencePuzzleGame() {
    val sentences = originalSentences.shuffled() // Oraciones desordenadas
    var solvedCount by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }

    LaunchedEffect(solvedCount) {
        if (solvedCount == sentences.size) {
            isGameOver = true
        }
    }

    Scaffold(
        content = {
            Column(modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())) {
                sentences.forEach { sentence ->
                    SentencePuzzle(sentence = sentence) { solved ->
                        if (solved) {
                            solvedCount += 1
                        }
                    }
                }

                if (isGameOver) {
                    Text("¡Juego terminado! Has resuelto todas las oraciones.", color = Color.Green)
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SentencePuzzleGamePreview() {
    OrtografiaMariamelTheme {
        SentencePuzzleGame()
    }
}
