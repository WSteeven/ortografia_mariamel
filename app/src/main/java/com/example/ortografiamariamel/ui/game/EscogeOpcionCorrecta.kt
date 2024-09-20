package com.example.ortografiamariamel.ui.game

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

data class Word3(
    val text: String,
    val correctAnswer: String
)

data class WordState3(
    val word: Word3,
    var selectedAnswer: MutableState<String?> = mutableStateOf(null)
)

val words3 = listOf(
    Word3("Tradu___ión", "cc"),
    Word3("Precau___ión", "c"),
    Word3("Instru___ión", "cc"),
    Word3("Satisfa___ión", "cc"),
    Word3("Rela__ión", "c"),
    Word3("Reda___ión", "cc"),
    Word3("Adi___ión", "cc"),
    Word3("Solu___ión", "c"),
    Word3("A___ión", "cc"),
    Word3("Produ___ión", "cc"),
    Word3("Condu___ión", "cc"),
    Word3("Dire___ión", "cc"),
)

@Composable
fun SpellingGameScreen(snackbarHostState: SnackbarHostState) {
    val wordsState = remember { mutableStateOf(words3.map { WordState3(it) }) }
    var showResult by remember { mutableStateOf<Pair<Boolean, List<WordState3>>?>(null) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        EnergyBar()
//        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            // Opciones de respuesta
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Palabras",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(.5f)
                    )
                    Text(
                        "Opciones",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                wordsState.value.forEach { wordState ->
                    Log.d("wordsState", "Dentro del 74: $wordState")
                    WordItem(wordState,snackbarHostState=snackbarHostState, onAnswerSelected = { answer ->
                        wordState.selectedAnswer.value = answer
                        Log.d("wordItem", "Dentro del wordItem: $answer´y $wordState")
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para verificar respuestas
        Button(onClick = {
            val isCorrect =
                wordsState.value.all { it.selectedAnswer.value == it.word.correctAnswer }
            showResult = Pair(isCorrect, wordsState.value)
        }) {
            Text("Verificar")
        }

        showResult?.let { (isCorrect, results) ->
            AlertDialog(
                onDismissRequest = { showResult = null },
                title = { Text(if (isCorrect) "¡Correcto!" else "Incorrecto") },
                text = {
                    Text(
                        buildString {
                            append("Tus respuestas:\n")
                            results.forEach {
                                append("${it.word.text}: ${it.selectedAnswer ?: "No respondida"}\n")
                            }
                        }
                    )
                },
                confirmButton = {
                    Button(onClick = { showResult = null }) {
                        Text("OK")
                    }
                }
            )
        }
    }
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }
}

@Composable
fun WordItem(wordState: WordState3, snackbarHostState: SnackbarHostState, onAnswerSelected: (String) -> Unit) {
    val options = listOf("c", "cc")
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(
            wordState.word.text,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.CenterVertically),
        )
        options.forEach { option ->
            OptionButton(
                text = option,
                isSelected = option == wordState.selectedAnswer.value,
                isCorrect = option == wordState.word.correctAnswer,
                onClick = {
                    snackbarMessage =
                        if (option == wordState.word.correctAnswer) "Correcto" else "Incorrecto"
                    wordState.selectedAnswer.value = option
                    onAnswerSelected(option)
                    showSnackbar = true
                    Log.d(
                        "wordItem",
                        "Opción seleccionada: $option, Estado: ${wordState.selectedAnswer}"
                    )
                })
        }
        if (showSnackbar) {
            LaunchedEffect(snackbarMessage) {
                snackbarMessage?.let {
                    snackbarHostState.showSnackbar(it)
                    snackbarMessage = null
                }
            }
        }

    }

}

@Composable
fun OptionButton(text: String, isSelected: Boolean, isCorrect: Boolean, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
            Log.d("Button", " Diste clic en $text, está seleccionado? $isSelected")
        },
        modifier = Modifier
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = when {
                isSelected && isCorrect -> Color.Green
                isSelected -> Color(240, 150, 55)
                else -> Color.Gray
            },
            contentColor = Color.White,
        )

    ) {
        Text(text)
    }
}


@Preview(showBackground = true)
@Composable
fun SpellingGamePreview() {
    OrtografiaMariamelTheme {
        SpellingGameScreen(remember { SnackbarHostState() })
    }
}