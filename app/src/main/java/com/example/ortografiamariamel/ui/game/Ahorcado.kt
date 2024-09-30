package com.example.ortografiamariamel.ui.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AhorcadoGame() {
    val palabraCorrecta by remember { mutableStateOf("ENUMERACION") }
    var letrasAdivinadas by remember { mutableStateOf(mutableStateListOf<Char>()) }
    var intentosFallidos by remember { mutableIntStateOf(0) }

    val intentosMaximos = 6

    fun seAdivinoPalabra() = palabraCorrecta.all { it in letrasAdivinadas }

    val ahorcado = when(intentosFallidos){
        1-> R.drawable.ahorcado_1
        2-> R.drawable.ahorcado_2
        3-> R.drawable.ahorcado_3
        4-> R.drawable.ahorcado_4
        5-> R.drawable.ahorcado_5
        6-> R.drawable.ahorcado_6
        else ->R.drawable.ahorcado_0

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = ahorcado), contentDescription = "Ahorcado")
        Text(
            text = "La coma se emplea para separar los componentes de una",
            modifier = Modifier.padding(16.dp)
        )

        // Mostrar el progreso del jugador
        Text(
            text = palabraCorrecta.map { if (it in letrasAdivinadas) it else '_' }.joinToString(" "),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Letras incorrectas
        Text(
            text = if(letrasAdivinadas.size>0)"Letras incorrectas: ${letrasAdivinadas.filter { it !in palabraCorrecta }.joinToString(", ")}" else "",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red
        )

        // Comprobar si se ha alcanzado el máximo de intentos
        if (intentosFallidos < intentosMaximos && !seAdivinoPalabra()) {
            AlphabetGrid { letter ->
                Log.d("Ahorcado", "Letra clickeada: $letter")
                letrasAdivinadas.add(letter)
                if (!palabraCorrecta.contains(letter)) {
                    intentosFallidos++
                }
            }
        } else {
            // Mostrar resultado
            val resultText = if (seAdivinoPalabra()) "¡Ganaste!" else "Perdiste. La palabra era: "
            Text(text = resultText)
            Text(text = palabraCorrecta, style = MaterialTheme.typography.headlineMedium)
            Button(onClick = {
                letrasAdivinadas = mutableStateListOf() // Reinicia el estado
                intentosFallidos = 0
            }) {
                Text("Jugar de nuevo")
            }
        }
    }
}

@Composable
fun AlphabetGrid(onLetterSelected: (Char) -> Unit) {
    val alphabet = ('A'..'Z').toList()
    Column {
        for (i in alphabet.indices step 6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (j in 0 until 6) {
                    if (i + j < alphabet.size) {
                        val letter = alphabet[i + j]
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { onLetterSelected(letter) }
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicText(text = letter.toString())
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AhorcadoPreview() {
    OrtografiaMariamelTheme {
        AhorcadoGame()
    }
}