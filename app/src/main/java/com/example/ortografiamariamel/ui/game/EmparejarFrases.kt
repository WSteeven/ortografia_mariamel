package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

val palabras = listOf(
    "NACIMIENTO",
    "CRECIMIENTO",
    "AGRADECIMIENTO",
    "CONOCIMIENTO",
    "ACONTECIMIENTO"
)
val frases = listOf(
    "Proceso de nacer.",
    "Acto de construir o crecer.",
    "Expresión de gratitud.",
    "Proceso de conocer o hacerse conocido.",
    "Evento, hecho o suceso."
)

@Composable
fun EmparejarFrases() {
    val shuffledPalabras = remember { palabras.shuffled().toMutableStateList() }
    val shuffledFrases = remember { frases.shuffled().toMutableStateList() }

    Column {
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
            items(shuffledPalabras.size){index ->
val palabra = shuffledPalabras[index]
                Button(onClick = { /*TODO*/ }) {
                    
                }
            }
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