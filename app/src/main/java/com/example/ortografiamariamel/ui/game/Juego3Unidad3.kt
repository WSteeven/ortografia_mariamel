package com.example.ortografiamariamel.ui.game

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    var selectedAnswer: String? = null
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
fun SpellingGameScreen() {
    val wordsState = remember { mutableStateOf(words3.map { WordState3(it) }) }
    var showResult by remember { mutableStateOf<Pair<Boolean, List<WordState3>>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Lista de palabras
//            Column(modifier = Modifier.weight(1f)) {
//                Text("Palabras", style = MaterialTheme.typography.titleLarge)
//                Spacer(modifier = Modifier.height(8.dp))
//                wordsState.value.forEach { wordState ->
//                    Text(text = wordState.word.text)
//                }
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))

            // Opciones de respuesta
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth()){
                Text("Palabras", style = MaterialTheme.typography.titleLarge, modifier = Modifier.fillMaxWidth(.5f))
                Text("Opciones", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 30.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                wordsState.value.forEach { wordState ->
                    WordItem(wordState, onAnswerSelected = { answer ->
                        wordState.selectedAnswer = answer
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para verificar respuestas
        Button(onClick = {
            val isCorrect = wordsState.value.all { it.selectedAnswer == it.word.correctAnswer }
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
}

@Composable
fun WordItem(wordState: WordState3, onAnswerSelected: (String) -> Unit) {
    val options = listOf("c", "cc")


    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(
            wordState.word.text,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.CenterVertically),
        )
        OptionButton(
            text = options[0],
            isSelected = options[0] == wordState.selectedAnswer,
            onClick = { onAnswerSelected(options[0]) }
        )
        OptionButton(
            text = options[1],
            isSelected = options[1] == wordState.selectedAnswer,
            onClick = { onAnswerSelected(options[1]) }
        )
    }

}

@Composable
fun OptionButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Blue else Color.Gray,
            contentColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Red
        )
    ) {
        Text(text)
    }
}

@Composable
fun AnswerOptions() {
    val options = listOf("c", "cc")

    Column {
        options.forEach { option ->
            Button(
                onClick = { /* Handle option selection here if needed */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(option)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpellingGamePreview() {
    OrtografiaMariamelTheme {
        SpellingGameScreen()
    }
}