package com.example.ortografiamariamel.ui.game

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun Juego2Prueba() {
SimpleDraggableWordView(wordState = WordState(Word("Texto"), false))
}

@Composable
fun SimpleDraggableWordView(wordState: WordState) {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var isDragging by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    Log.d("SimpleDraggableWordView", "detectDragGestures::change $change ")
                    Log.d("SimpleDraggableWordView", "detectDragGestures::change $dragAmount ")
                    Log.d("SimpleDraggableWordView", "detectDragGestures::change $wordState ")
                    offset = Offset(offset.x + dragAmount.x, offset.y + dragAmount.y)
                    change.consume()
                    isDragging = true
                }
                detectDragGesturesAfterLongPress { change, _ ->
                    Log.d("SimpleDraggableWordView", "Long press $change")
                    if (isDragging) {
                        isDragging = false
                        Log.d("SimpleDraggableWordView", "Drag ended at $offset")
                    }
                }
            }
            .background(Color.Blue, RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(text = wordState.word.text, fontSize = 12.sp, color = Color.White)
    }
}
