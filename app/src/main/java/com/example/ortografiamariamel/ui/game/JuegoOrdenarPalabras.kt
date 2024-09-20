package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun OrdenarPalabras() {
    val palabras = listOf("FLOREZCA", "LUZCO", "PERMANEZCO", "CONDUZCO")
    var respuestas by remember { mutableStateOf(List(palabras.size) { "" }) }
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

// Generar las palabras mezcladas solo una vez
    val palabrasMezcladas = palabras.map { it.toList().shuffled().joinToString("") }

    Column(modifier = Modifier.fillMaxSize()) {
        palabras.forEachIndexed { index, palabra ->
            val imageId = when (index) {
                0 -> R.drawable.florezco
                1 -> R.drawable.luzco
                2 -> R.drawable.permanezco
                3 -> R.drawable.conduzco
                else -> -1
            }

            ImageWordComponent(
                imagen = imageId,
                palabra = palabra,
                palabraMezclada = palabrasMezcladas[index],
                respuestas = respuestas[index],
                onRespuestaChange = { nuevaRespuesta ->
                    respuestas = respuestas.toMutableList().apply { this[index] = nuevaRespuesta }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = {
            val allCorrect = respuestas.zip(palabras).all { (respuesta, palabra) ->
                respuesta.equals(palabra, ignoreCase = true)
            }
            message = if (allCorrect) "¡Felicitaciones! Todas las palabras están correctas." else "Algunas palabras están incorrectas."
            showDialog = true
        },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            border = BorderStroke(4.dp, Color(244, 225, 220)),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(240, 150, 55), contentColor = Color.White
            )
        ) {
            Text("Verificar", fontSize = 30.sp)
        }

        // Diálogo de resultado
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Resultado") },
                text = { Text(message) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
fun ImageWordComponent(imagen: Int, palabra: String, palabraMezclada:String, respuestas: String, onRespuestaChange: (String) -> Unit) {
    Image(
        painter = painterResource(imagen),
        contentDescription = palabra,
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .padding(4.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )

    // Muestra la palabra mezclada
    Text(
//        text = palabra.toList().shuffled().joinToString(""),
        text = palabraMezclada,
        letterSpacing = 2.sp,
        color = Color(255, 168, 0),
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in palabra.indices) {
            var inputText by remember { mutableStateOf("") }

            BasicTextField(
                value = inputText,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        inputText = newValue
                        onRespuestaChange(respuestas.padEnd(palabra.length).replaceRange(i, i + 1, newValue))
                    }
                },
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrdenarPalabrasScreenPreview() {
    OrtografiaMariamelTheme {
        OrdenarPalabras()
    }
}
