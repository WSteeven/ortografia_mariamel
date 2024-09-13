package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class Sentence(
    val id: Int,
    val text: String,
    val correctWord: String
)

data class Word(val text: String)

val sentences = listOf(
    Sentence(
        1,
        "El __________ de la empresa ha sido muy notable en los últimos años.",
        "crecimiento"
    ),
    Sentence(
        2,
        "El __________ de los lazos familiares es esencial para una vida en armonía.",
        "fortalecimiento"
    ),
    Sentence(3, "Quiero expresar mi profundo __________ por toda tu ayuda.", "agradecimiento"),
    Sentence(4, "El nuevo __________ de la tienda estará en el centro comercial.", "local"),
    Sentence(
        5,
        "El __________ de nuevas habilidades es crucial para tu desarrollo profesional.",
        "aprendizaje"
    )
)

val words = listOf(
    Word("crecimiento"),
    Word("fortalecimiento"),
    Word("agradecimiento"),
    Word("conocimiento"),
    Word("establecimiento")
)

data class SentenceState(val sentence: Sentence, var isCompleted: Boolean = false) {
    fun getTextWithPlaceholder(): String {
        return sentence.text.replace("__________", "__________")
    }
}

data class WordState(val word: Word, var isUsed: Boolean = false)



@Composable
fun WordGameScreen() {
    val sentencesState = remember { mutableStateOf(sentences.map { SentenceState(it) }) }
    val wordsState = remember { mutableStateOf(words.map { WordState(it) }) }
    var draggingWord by remember { mutableStateOf<Word?>(null) }
    var showDialog by remember { mutableStateOf<Pair<Boolean, String>?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        sentencesState.value.forEach { sentenceState ->
            SentenceView(sentenceState, onWordDropped = { word ->
                if (word.text == sentenceState.sentence.correctWord) {
                    showDialog = Pair(true, "¡Correcto!")
                    sentenceState.isCompleted = true
                } else {
                    showDialog = Pair(false, "Incorrecto. Intenta de nuevo.")
                }
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        wordsState.value.forEach { wordState ->
            if (!wordState.isUsed) {
                DraggableWordView(wordState){ draggingWord = wordState.word}
            }
        }
    }

    showDialog?.let { (isCorrect, message) ->
        AlertDialog(
            onDismissRequest = { showDialog = null },
            title = { Text("Resultado") },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { showDialog = null }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun SentenceView(sentenceState: SentenceState, onWordDropped: (Word) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = sentenceState.getTextWithPlaceholder(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
        if (!sentenceState.isCompleted) {
            SentenceDropTarget(
                modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.Gray),
                onWordDropped = {
                    onWordDropped(Word("Agradecimiento"))}
            )
        }
    }
}

@Composable
fun DraggableWordView(wordState: WordState, onDragEnd: () -> Unit) {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var isDragging by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offset = Offset(offset.x + dragAmount.x, offset.y + dragAmount.y)
                    change.consume() // Consume the change to avoid further propagation
                    isDragging = true
                }
                // Detect when dragging ends
                detectDragGestures { change, _ ->
                    if (isDragging) {
                        isDragging = false
                        onDragEnd()
                    }
                }
            }
            .background(Color.Blue, RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(text = wordState.word.text, color = Color.White)
    }
}

@Composable
fun SentenceDropTarget(
    modifier: Modifier,
    onWordDropped: () -> Unit // No parámetros, solo notifica que la palabra se ha soltado
) {
    // Track the size and position of the drop target
    var targetRect by remember { mutableStateOf(Rect.Zero) }
    var isHovered by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(Color.Gray)
            .padding(8.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val currentPosition = change.position
                    if (isHovered && targetRect.contains(currentPosition)) {
                        onWordDropped() // Notify that a word has been dropped within bounds
                    }
                }
            }
            .onGloballyPositioned { coordinates ->
                targetRect = coordinates.boundsInWindow() // Update bounds of drop target
            }
    ) {
        // Representing the drop target area
        // Placeholder for visual feedback if needed
        if (isHovered) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray.copy(alpha = 0.3f))
            )
        }
    }
}
