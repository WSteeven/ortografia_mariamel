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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

val oraciones = listOf(
    Word3("Compré manzanas, peras, naranjas y plátanos.", "V"),
    Word3("María fue al supermercado y compró pan, leche pizza.", "F"),
    Word3("En la reunión asistieron Juan, Marta, Luis, y Ana.", "V"),
    Word3("Necesito buscar mis zapatos, mi chaqueta mi cartera.", "F"),
    Word3("Los colores de la bandera son rojo, blanco y azul.", "V"),
    Word3("Para la receta necesitas, huevos, harina azúcar.", "F"),
)

@Composable
fun EscogeVerdaderoFalso(snackbarHostState: SnackbarHostState) {
    val wordsState = remember { mutableStateOf(oraciones.map { WordState3(it) }) }
    var showResult by remember { mutableStateOf<Pair<Boolean, List<WordState3>>?>(null) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Oraciones",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(.7f)
                    )
                    Text(
                        "Opciones",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                wordsState.value.forEach{wordState ->
                    Item(wordState, snackbarHostState=snackbarHostState, onAnswerSelected = {answer ->
                        wordState.selectedAnswer.value = answer
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para verificar respuestas
        Button(onClick = {
            val isCorrect = wordsState.value.all { it.selectedAnswer.value == it.word.correctAnswer }
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
                            append("Todas tus respuestas han sido "+ if(isCorrect) "correctas" else "incorrectas")
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
fun Item(wordState: WordState3, snackbarHostState: SnackbarHostState, onAnswerSelected: (String) -> Unit) {
    val options = listOf("V", "F")
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(
            wordState.word.text,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .align(Alignment.CenterVertically)
                .padding(end = 4.dp),
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
                },
            )
//            Spacer(modifier = Modifier.padding(4.dp))
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


@Preview(showBackground = true)
@Composable
fun EscogeVerdaderoFalsoPreview() {
    OrtografiaMariamelTheme {
        EscogeVerdaderoFalso(remember { SnackbarHostState() })
    }
}