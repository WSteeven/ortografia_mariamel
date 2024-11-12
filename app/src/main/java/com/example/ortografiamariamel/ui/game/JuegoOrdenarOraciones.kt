package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun GameScreen(sentence: String, onSolved: (Boolean) -> Unit) {
    // Desordena la oración
    val originalWords = sentence.split(" ")
    val shuffledWords = remember { originalWords.shuffled().toMutableStateList() }
    val userOrder = remember { mutableStateListOf<String>() }
    // Aseguramos que wordStatus tenga el tamaño adecuado, inicializándolo con el tamaño de originalWords
    val wordStatus = remember { mutableStateListOf<Boolean?>().apply { repeat(originalWords.size) { add(null) } } }
    var timer by remember { mutableIntStateOf(1) } // Tiempo en segundos
    var isSolved by remember { mutableStateOf(false) }
//    var isGameOver by remember { mutableStateOf(false) }

    // Temporizador
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }
//    LaunchedEffect(isGameOver) {
//        if(isGameOver){
//            onSolved(true)
//        }
//    }

    if(!isSolved) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//        Spacer(modifier = Modifier.height(100.dp))
            // Sección de palabras desordenadas
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
                items(shuffledWords.size) { index ->
                    val word = shuffledWords[index]
                    ClickableText(
                        text = AnnotatedString(word),
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(8.dp),
                        onClick = {
                            userOrder.add(word)
                            shuffledWords.remove(word)
                            timer = 2
                            // Actualiza el estado de la palabra según la posición
                            wordStatus[userOrder.size - 1] =
                                userOrder[userOrder.size - 1] == originalWords[userOrder.size - 1]
                            if (userOrder.toList() == originalWords) {
                                isSolved=true
                                onSolved(true)

                            }
                        }
                    )
                }
            }
            // Sección donde se muestra el orden del usuario
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(userOrder.size) { index ->
                    val word = userOrder[index]
                    val isCorrect = wordStatus[index]
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(if (isCorrect == true) Color.Green else if (isCorrect == false) Color.Red else Color.Transparent)
                            .padding(8.dp)
                    ) {
                        ClickableText(
                            text = AnnotatedString(word),
                            onClick = {
                                userOrder.remove(word)
                                shuffledWords.add(word)
                                // Actualiza el estado de la palabra según la posición
//                            wordStatus[userOrder.size - 1] = userOrder[userOrder.size - 1] == originalWords[userOrder.size - 1]
                                // Actualiza el estado solo de la última palabra seleccionada
                                wordStatus[userOrder.size] = userOrder.getOrNull(userOrder.size)
                                    ?.let { it == originalWords[userOrder.size] }
                            }
                        )
//                    Text(text = word)
                        if (isCorrect != null && timer > 0) {
                            Icon(
                                painter = painterResource(
                                    if (isCorrect) android.R.drawable.star_big_on else android.R.drawable.ic_delete
                                ),
                                contentDescription = if (isCorrect) "Correcto" else "Incorrecto",
                                tint = if (isCorrect) Color.Yellow else Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }

            // Botón de verificación
//            Button(onClick = {
//                Log.d(
//                    "OrdenarOracion",
//                    "verificacion de valores ${userOrder.toList()}, $originalWords"
//                )
//                if (userOrder.toList() == originalWords) {
//                    Log.d("OrdenarOracion", "¡Correcto!")
//                    isSolved=true
//                    onSolved(true)
//                } else {
//                    Log.d("OrdenarOracion", "Inténtalo de nuevo")
//                    isSolved=false
//                    onSolved(false)
//                }
//            }) {
//                Text("Verificar")
//            }
        }
    }else{
        // Aqui no se muestra nada
    }
}

@Preview(showBackground = true)
@Composable
fun JuegoOrdenarOracionesDefaultPreview() {
    GameScreen("Tú eres mi mejor amigo"){}
}