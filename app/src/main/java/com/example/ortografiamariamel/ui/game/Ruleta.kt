package com.example.ortografiamariamel.ui.game


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GiraYJuega() {
    val palabras = listOf("MERECER", "CRECER", "EMBELLECER", "PRODUCIR", "ACONTECER", "ESTREMECER")
    val palabrasCorrectas =
        listOf("MEREZCO", "CREZCO", "EMBELLEZCO", "PRODUZCO", "ACONTEZCO", "ESTREMEZCO")
    var palabraSeleccionada by remember { mutableStateOf("") }
    var respuesta by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val palabrasTachadas = remember { mutableStateListOf<String>() }

    // Animación de Lottie
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ruleta))
    val animatable = rememberLottieAnimatable()

    var isSpinning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val palabrasDisponibles = palabras.filter{it!in palabrasTachadas}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar palabras tachadas
        // Mostrar las palabras con un estilo tachado si ya fueron adivinadas
//        Column {
//            palabras.forEach { palabra ->
//                Text(
//                    text = palabra,
//                    fontWeight = if (palabrasTachadas.contains(palabra)) FontWeight.Bold else FontWeight.Normal,
//                    color = if (palabrasTachadas.contains(palabra)) Color.Gray else Color.Black,
//                    modifier = Modifier.padding(2.dp).border(BorderStroke(1.dp, Color.LightGray))
//                )
//            }
//        }
        Box(modifier = Modifier.height(100.dp)) { // Ocupa el espacio restante
//            LazyHorizontalGrid(rows = GridCells.Fixed(2),
//                modifier = Modifier.fillMaxSize(),
//                content = {
//                    items(palabras.size) { index ->
//                        val palabra = palabras[index]
//                        Text(
//                            text = palabra,
//                            fontWeight = if (palabrasTachadas.contains(palabra)) FontWeight.Bold else FontWeight.Normal,
//                            color = if (palabrasTachadas.contains(palabra)) Color.Gray else Color.Black,
//                            maxLines = 1,
//                            modifier = Modifier
//                                .padding(2.dp)
//                                .border(BorderStroke(1.dp, Color.LightGray))
//                                .fillMaxWidth()
//                                .width(150.dp)
//                                .widthIn(max = 150.dp) // Limita el ancho máximo
//                                .wrapContentWidth(Alignment.CenterHorizontally)
//
//                        )
//                    }
//                }
//            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(palabras.size) { index ->
                        val palabra = palabras[index]
                        Text(
                            text = palabra,
                            fontWeight = if (palabrasTachadas.contains(palabra)) FontWeight.Bold else FontWeight.Normal,
                            color = if (palabrasTachadas.contains(palabra)) Color.Gray else Color.Black,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(2.dp)
                                .border(BorderStroke(1.dp, Color.LightGray))
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.Start)

                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            // Mostrar la animación de Lottie
            LottieAnimation(
                composition = composition,
                iterations = if (isSpinning) LottieConstants.IterateForever else 1,
                modifier = Modifier.size(400.dp)
            )

            Button(
                onClick = {
                    if(palabrasDisponibles.isNotEmpty()){
                    isSpinning = true
                    scope.launch {
                        animatable.animate(composition)
                        delay(2000) // Esperar 2 segundos mientras la animación gira
                        palabraSeleccionada = palabrasDisponibles[Random.nextInt(palabrasDisponibles.size)]
                        isSpinning = false
                    }}else{
                        message = "¡Felicidades! Ya has resuelto todas las palabras."
                        showDialog = true
                    }
                },
                border = BorderStroke(4.dp, Color(244, 225, 220)),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(240, 150, 55), contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text("Girar", fontSize = 25.sp)
            }
        }

        if (palabraSeleccionada.isNotEmpty() && !isSpinning) {
            Row {
                Text(text = "Palabra: ")
                Text(text = palabraSeleccionada, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = respuesta,
                onValueChange = { respuesta = it },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrect = true,
                    imeAction = ImeAction.Done
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val index = palabras.indexOf(palabraSeleccionada)
                    if (respuesta.equals(palabrasCorrectas[index], ignoreCase = true)) {
                        palabrasTachadas.add(palabraSeleccionada)
                        message = "¡Correcto! La palabra es ${palabrasCorrectas[index]}."
                    } else {
                        message = "Incorrecto. La palabra correcta es ${palabrasCorrectas[index]}."
                    }
                    showDialog = true
                },
                border = BorderStroke(4.dp, Color(244, 225, 220)),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(240, 150, 55), contentColor = Color.White
                ),
            ) {
                Text("Verificar", fontSize = 30.sp)
            }
        }

        // Diálogo de resultado
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Resultado") },
                text = { Text(message) },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        respuesta = ""
                        palabraSeleccionada = ""
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun RuletaScreenPreview() {
    OrtografiaMariamelTheme {
        GiraYJuega()
    }
}
