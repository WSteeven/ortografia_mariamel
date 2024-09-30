package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

val oracionesCorrectas = listOf(
    "Compré tomates, cebollas y pimientos para la salsa",
    "En la mochila llevo lápices, libros y cuadernos",
    "Ana, Juan y Luis irán a la fiesta esta noche",
    "Necesitamos tijeras, pegamento y papel para la manualidad",
    "En el parque vi patos, perros y gatos jugando",
    "Para el postre necesito azúcar, leche y vainilla",
)
val respondidasCorrectas = mutableListOf(false,false,false, false, false,false)

@Composable
fun Dictado() {
    val soundManager = SoundManager(LocalContext.current)
    var showResult by remember { mutableStateOf(false) }
    fun getAudio(index: Int): Int {
        return when (index) {
            0 -> R.raw.audio1
            1 -> R.raw.audio2
            2 -> R.raw.audio3
            3 -> R.raw.audio4
            4 -> R.raw.audio5
            5 -> R.raw.audio6
            else -> -1
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        oracionesCorrectas.forEachIndexed { index, oracion ->
            AudioOracion(
                soundManager,
                getAudio(index),
                index,
                oracion
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }

        Button(
            onClick = { showResult = true },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            border = BorderStroke(4.dp, Color(244, 225, 220)),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(240, 150, 55), contentColor = Color.White
            ),
        ) {
            Text("Verificar")
        }
        if (showResult) {
            val totalCorrectas = respondidasCorrectas.count{it}
            AlertDialog(onDismissRequest = { showResult = false },
                title = { Text(if (totalCorrectas == oracionesCorrectas.size) "¡Correcto!" else "Incorrecto") },
                text = { Text("Has respondido $totalCorrectas de ${oracionesCorrectas.size} oraciones correctas " + if (totalCorrectas < oracionesCorrectas.size) ".\nVuelve escuchar los audios y completa correctamente" else ". ") },
                confirmButton = { Button(onClick = { showResult = false }) { Text("OK") } })
        }
    }
}

@Composable
fun AudioOracion(
    soundManager: SoundManager,
    audio: Int,
    posicion:Int,
    oracion: String,
) {
    var oracionIngresada by remember { mutableStateOf("") }
    var borderColor by remember { mutableStateOf(Color(255, 168, 0)) }
    Row(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { soundManager.playSound(audio) },
            modifier = Modifier.padding(end = 0.dp) // Espacio entre los botones
        ) {
            Icon(
                painter = painterResource(id = R.drawable.speaker_filled),
                contentDescription = "audio_speaker",
                modifier = Modifier
                    .padding(end = 20.dp) // Espacio a la derecha de la imagen
            )
            Image(
                painter = painterResource(id = R.drawable.lapiz6),
                contentDescription = "Audio",
                modifier = Modifier
                    .padding(start = 20.dp) // Espacio a la derecha de la imagen
                    .size(48.dp) // Tamaño deseado de la imagen
            )
        }

        TextField(
            value = oracionIngresada,
            onValueChange = {
                oracionIngresada = it
                borderColor = when {
                    it.isEmpty() -> Color(255, 168, 0)
                    it.trim() == oracion -> {
                        respondidasCorrectas[posicion]= true
                        Color.Green
                    }

                    else -> {
                        respondidasCorrectas[posicion] = false
                        Color.Red
                    }
                }
            },
            singleLine = false,
            modifier = Modifier
                .border(BorderStroke(width = 2.dp, color = borderColor)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                imeAction = ImeAction.Done
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DictadoPreview() {
    OrtografiaMariamelTheme {
        Dictado()
    }
}

@Preview(showBackground = true)
@Composable
fun AudioOracionPreview() {
    OrtografiaMariamelTheme {
        AudioOracion(SoundManager(LocalContext.current), 1, 1,"Mi oracion personalizada")
    }
}