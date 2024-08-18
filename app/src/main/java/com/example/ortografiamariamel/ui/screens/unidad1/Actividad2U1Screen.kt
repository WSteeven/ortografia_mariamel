package com.example.ortografiamariamel.ui.screens.unidad1

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

data class Question(
    val index:Int,
    val question: String,
    val options: List<String>,
    val correctOption: Int, // Índice de la respuesta correcta
    val respuestas:Int=1
)

val questions = listOf(
    Question(1,"__ espero para tomar __ __", listOf("París", "Londres", "Berlín"), 0, 3),
//    Question("Te/té espero para tomar el/él té/te", listOf("París", "Londres", "Berlín"), 0),
//              __                      __    __

    Question(2,"__ eres mi mejor amigo", listOf("5", "6", "7"), 2,1),
//                    Tú/tu
//                    __

    Question(3, "Dice que __ libro es __ __.", listOf("5", "6", "7"), 2,3),
//                             el/él  de/dé   el/él
//                             __     __         __

    Question(4,"__ me lo contó en la clase.", listOf("5", "6", "7"), 2,1),
//                    El/Él
//                       __

    Question(5,"Ya __ que no __ gusta el __.", listOf("5", "6", "7"), 2,3),
//                      se/sé      te/té    te/té
//                         __      __          __

    Question(6,"__ tienes siempre la razón.", listOf("5", "6", "7"), 2, 1),
//                    Tú/tu
//                    __

    Question(7,"Espero que __ lo __ a él.", listOf("5", "6", "7"), 2,2),
//                            se/sé    dé/de
//                            __       __


)


@Composable
fun Actividad2U1(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {

    MenuLateral(
        title = R.string.blank,
        content = {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End,
                        modifier = modifier
                            .fillMaxWidth(.4f)
                            .fillMaxHeight(.07f)
                    ) {
                        LottieAnimationA2U1Screen()
                    }
                }

                Text(
                    text = "Seleccione el  monosílabo en cada oración",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp,
                    color = Color(230, 170, 75),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )

//                GameApp(viewModel)
                LadderScreen(viewModel)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_small)),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onPrevButtonClicked
                        ) {
                            Text(stringResource(R.string.atras))
                        }
                    }
                }
            }
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
}

@Composable
fun MenuDropdown(options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row {
            TextField(
                value = valor,
                onValueChange = { valor = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(.3f)
                    .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
                enabled=false,
                suffix = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )

        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        valor = option
                        selectedOption = option
                        showDialog = true
                    },
                )
            }
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameApp(viewModel: AppViewModel) {
    var currentLevel by remember { mutableIntStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }


    EnergyBar(viewModel.uiState.value.energiasJuego2U1)
    if (gameOver) {
        Text(text = "Juego terminado")
    } else if (currentLevel < questions.size) {
        GameScreen(currentLevel) { isCorrect ->
            if (isCorrect) {
                currentLevel++
            } else {
                gameOver = true
            }
        }
    } else {
        Text(text = "¡Felicidades! Has terminado el juego.")
    }
}

@Composable
fun GameScreen(currentLevel: Int, onAnswerSelected: (Boolean) -> Unit) {
    val question = questions[currentLevel]

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nivel ${currentLevel + 1}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = question.question, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEachIndexed { index, option ->
            Button(onClick = {
                onAnswerSelected(index == question.correctOption)
            }) {
                Text(text = option)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LadderScreen(viewModel: AppViewModel) {
    var selectedButton by remember { mutableIntStateOf(-1) } // -1 para indicar que ningún botón está seleccionado
    var showDialog by remember { mutableStateOf(false) }
    EnergyBar(viewModel.uiState.value.energiasJuego2U1)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 7 downTo 1) {
            Button(
                onClick = {
                    selectedButton = i
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Botón $i")
            }
        }

        // Mostrar el modal si showDialog es true
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Botón Seleccionado")
                },
                text = {
                    MenuDropdown(listOf("Te", "Té"))
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

@Composable
fun ContenidoOracion1(){
    val oracion = questions.first { it->it.index==1 }
    Text(text= oracion.toString())

}

@Composable
fun Dialogo(abierto: Boolean = false, selectedOption: String = "") {
    var showDialog by remember { mutableStateOf(abierto) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Botón Seleccionado")
            },
            text = {
                Text(text = "Has seleccionado el Botón $selectedOption")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Composable
fun LottieAnimationA2U1Screen() {
//    val context = LocalContext.current

    // Cargar la animación desde assets
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.actividad_apareciendo))

    // Controlar la animación
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        animatable.animate(composition)
    }

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun Actividad2ScreenPreview() {
    OrtografiaMariamelTheme {
        Actividad2U1(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {},
            onNextButtonClicked = { /*TODO*/ })
    }
}

@Preview(showBackground = true)
@Composable
fun LadderScreenScreenPreview() {
    OrtografiaMariamelTheme {
        LadderScreen(viewModel = viewModel(factory = AppViewModelProvider.Factory),)
    }
}