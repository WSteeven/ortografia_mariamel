package com.example.ortografiamariamel.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay

@Composable
fun EvaluacionScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    MenuLateral(
//        title = R.string.blank, // AppScreen.Unidad2.title,
        title = R.string.blank, // AppScreen.Unidad2.title,
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(.9f)
                        .padding(4.dp)
                        .verticalScroll(rememberScrollState()) // Habilitar desplazamiento vertical
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                        Text(
                            text = "EVALUACION",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 64.dp) // Espacio al final del texto
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                    }

                    Text(
                        text = "Aquí va la evaluación",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 3.sp,
                        color = Color(230, 170, 75),
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                    )

                    Evaluacion(viewModel = viewModel, snackbarHostState = snackbarHostState)
//                    Evaluacion()

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
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
    SnackbarHost(hostState = snackbarHostState)
}

@Composable
fun Evaluacion(viewModel: AppViewModel, snackbarHostState: SnackbarHostState) {
    var selectedButton by remember { mutableIntStateOf(-1) } // -1 para indicar que ningún botón está seleccionado
    var showDialog by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.juego2_1),
            contentDescription = "Evaluacion Final",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
//            ComposableButton(
//                id = 5, onButtonClick = {
//                    selectedButton = it
//                    showDialog = true
//                }, modifier = Modifier
////                .padding(end = 20.dp)
//            )

            ComposableButton(
                id = 4, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                }, modifier = Modifier
//                .padding(end= 60.dp)
            )

            ComposableButton(
                id = 3, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                }, modifier = Modifier
//                .padding(start = 60.dp)
            )

            ComposableButton(id = 2, onButtonClick = {
                selectedButton = it
                showDialog = true
            }, modifier = Modifier)

            ComposableButton(
                id = 1, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                }, modifier = Modifier
//                .padding(start= 20.dp)
            )

        }

        if (showDialog) {
            MinimalDialog(viewModel = viewModel,
                index = selectedButton,
                onDismissRequest = { showDialog = false },
                onVerification = { isCorrect ->
                    snackbarMessage =
                        if (isCorrect) "¡Respuesta correcta!" else "Respuesta incorrecta"
                })

        }
    }
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }
}


@Composable
fun MinimalDialog(
    viewModel: AppViewModel,
    index: Int,
    onDismissRequest: () -> Unit,
    onVerification: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(4.dp)) {
                Timer(seconds = 30, onTimeUp = onDismissRequest)

                when (index) {
                    1 -> EvaluacionTildeDiacritica(
                        viewModel,
                        onClose = onDismissRequest,
                        onVerification
                    )

                    2 -> EvaluacionUsoDeC(
                        viewModel,
                        onClose = onDismissRequest,
                        onVerification
                    )

                    3 -> EvaluacionUsoDeZ(
                        viewModel,
                        onClose = onDismissRequest,
                        onVerification
                    )

                    4 -> EvaluacionUsoComa(
                        viewModel,
                        onClose = onDismissRequest,
                        onVerification
                    )

                    else -> Log.d("ACTIVIDAD2-U1", "No hay pregunta para este boton $index")

                }
            }
        }
    }
}

@Composable
fun EvaluacionUsoComa(
    viewModel: AppViewModel,
    onClose: () -> Unit,
    onVerification: (Boolean) -> Unit
) {
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    fun verificarRespuestas() {
        var score = 0
        if (respuesta1 == "C) platos, vasos, servilletas, decoraciones.") score++
        if (respuesta2 == "B) azules, rojos, verdes, amarillos.") score++
        if (score ==2) onVerification(true) else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.tema_unidad_4),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            color = Color(230, 170, 75),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Elige la opción correcta que complete las oraciones con el uso adecuado de la coma en las enumeraciones.",
            fontSize=10.sp
        )
        Text(
            text = "1. Para la fiesta necesitamos, platos __________ vasos __________ servilletas __________ decoraciones.",
            fontWeight = FontWeight.Bold
        )
        Column {
            RadioButtonOption(
                text = "A) platos, vasos, servilletas decoraciones.",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "A) platos, vasos, servilletas decoraciones." })
            RadioButtonOption(
                text = "B) platos vasos, servilletas, decoraciones.",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "B) platos vasos, servilletas, decoraciones." })
            RadioButtonOption(
                text = "C) platos, vasos, servilletas, decoraciones.",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "C) platos, vasos, servilletas, decoraciones." })
        }
        Text(
            text = "2. Me gustan los colores azules __________ rojos __________ verdes __________ amarillos.",
            fontWeight = FontWeight.Bold
        )
        Column {
            RadioButtonOption(
                text = "A) azules, rojos verdes amarillos.",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "A) azules, rojos verdes amarillos." })
            RadioButtonOption(
                text = "B) azules, rojos, verdes, amarillos.",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "B) azules, rojos, verdes, amarillos." })
            RadioButtonOption(
                text = "C) azules, rojos verdes, amarillos.",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "C) azules, rojos verdes, amarillos." })
        }

        BotonVerificarRespuesta {
            verificarRespuestas()
        }
    }
}

@Composable
fun EvaluacionUsoDeZ(
    viewModel: AppViewModel,
    onClose: () -> Unit,
    onVerification: (Boolean) -> Unit
) {
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    fun verificarRespuestas() {
        var score = 0
        if (respuesta1 == "A) conduzco") score++
        if (respuesta2 == "C) aparezco") score++
        if (score ==2) onVerification(true) else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.tema_unidad_3),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            color = Color(230, 170, 75),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Elige la opción correcta para completar cada oración.",
        )
        Text(
            text = "Yo __________ rápido porque tengo prisa.",
            fontWeight = FontWeight.Bold
        )
        Column {
            RadioButtonOption(
                text = "A) conduzco",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "A) conduzco" })
            RadioButtonOption(
                text = "B) conduco",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "B) conduco" })
            RadioButtonOption(
                text = "C) conduzgo",
                selectedOption = respuesta1,
                onOptionSelected = { respuesta1 = "C) conduzgo" })
        }
        Text(
            text = "En la clase de teatro, siempre __________ en escenas importantes.",
            fontWeight = FontWeight.Bold
        )
        Column {
            RadioButtonOption(
                text = "A) aparezo",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "A) aparezo" })
            RadioButtonOption(
                text = "B) aparesco",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "B) aparesco" })
            RadioButtonOption(
                text = "C) aparezco",
                selectedOption = respuesta2,
                onOptionSelected = { respuesta2 = "C) aparezco" })
        }

        BotonVerificarRespuesta {
            verificarRespuestas()
        }
    }
}

@Composable
fun EvaluacionUsoDeC(
    viewModel: AppViewModel,
    onClose: () -> Unit,
    onVerification: (Boolean) -> Unit
) {
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    var respuesta3 by remember { mutableStateOf("") }
    var respuesta4 by remember { mutableStateOf("") }
    var respuesta5 by remember { mutableStateOf("") }

    fun verificarRespuestas() {
        var score = 0
        if (respuesta1 == "Abastecimiento") score++
        if (respuesta2 == "Reconocimiento") score++
        if (respuesta3 == "Conocimiento") score++
        if (respuesta4 == "Establecimiento") score++
        if (respuesta5 == "Fortalecimiento") score++
        if (score ==5) onVerification(true) else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.tema_unidad_2),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            color = Color(230, 170, 75),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Indica las palabras que están escritas correctamente.",
            fontWeight = FontWeight.Bold
        )
        FilaOpciones("Abastesimiento", "Abastecimiento", onOptionSelected = { respuesta1 = it })
        FilaOpciones("Reconosimiento", "Reconocimiento", onOptionSelected = { respuesta2 = it })
        FilaOpciones("Conocimiento", "Conosimiento", onOptionSelected = { respuesta3 = it })
        FilaOpciones("Establesimiento", "Establecimiento", onOptionSelected = { respuesta4 = it })
        FilaOpciones("Fortalecimiento", "Fortalesimiento", onOptionSelected = { respuesta5 = it })

        BotonVerificarRespuesta {
            verificarRespuestas()
        }
    }
}

@Composable
fun FilaOpciones(opcion1: String, opcion2: String, onOptionSelected: (String) -> Unit) {
    var respuestaSeleccionada by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        ClickableText(
            text = AnnotatedString(opcion1, spanStyle = SpanStyle(fontSize = 12.sp)),
            modifier = Modifier
                .weight(0.45f) // 45% del espacio disponible
                .background(
                    if (respuestaSeleccionada == opcion1) Color.Green else Color.LightGray
                )
                .padding(8.dp),
            onClick = {
                respuestaSeleccionada = opcion1
                onOptionSelected(opcion1)
            }
        )
        Text("VS", fontSize=12.sp, textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        ClickableText(
            text = AnnotatedString(opcion2, spanStyle = SpanStyle(fontSize = 12.sp)),
            modifier = Modifier
                .weight(0.45f) // 45% del espacio disponible
                .background(if (respuestaSeleccionada == opcion2) Color.Green else Color.LightGray)
                .padding(8.dp),
            onClick = {
                respuestaSeleccionada = opcion2
                onOptionSelected(opcion2)
            }
        )

    }
}

@Composable
fun EvaluacionTildeDiacritica(
    viewModel: AppViewModel,
    onClose: () -> Unit,
    onVerification: (Boolean) -> Unit
) {
    var respuestaPregunta1 by remember { mutableStateOf("") }
    var respuestaPregunta2 by remember { mutableStateOf("") }

    fun verificarRespuestas() {
        var score = 0
        if (respuestaPregunta1 == "El") score++
        if (respuestaPregunta2 == "Ti") score++
        if (score == 2) onVerification(true) else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "La tilde diacrítica en los monosílabos",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            color = Color(230, 170, 75),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(text = "1. ¿Qué palabra lleva la tilde diacrítica?", fontWeight = FontWeight.Bold)
        Column {
            RadioButtonOption(
                text = "Sol",
                selectedOption = respuestaPregunta1,
                onOptionSelected = { respuestaPregunta1 = "Sol" })
            RadioButtonOption(
                text = "El",
                selectedOption = respuestaPregunta1,
                onOptionSelected = { respuestaPregunta1 = "El" })
            RadioButtonOption(
                text = "Que",
                selectedOption = respuestaPregunta1,
                onOptionSelected = { respuestaPregunta1 = "Que" })
            RadioButtonOption(
                text = "Porque",
                selectedOption = respuestaPregunta1,
                onOptionSelected = { respuestaPregunta1 = "Porque" })
        }
// Pregunta 2
        Text(text = "2. ¿Qué palabra no lleva la tilde diacrítica?", fontWeight = FontWeight.Bold)
        Column {

            RadioButtonOption(
                text = "Ti",
                selectedOption = respuestaPregunta2,
                onOptionSelected = { respuestaPregunta2 = "Ti" })
            RadioButtonOption(
                text = "El",
                selectedOption = respuestaPregunta2,
                onOptionSelected = { respuestaPregunta2 = "El" })
            RadioButtonOption(
                text = "Tu",
                selectedOption = respuestaPregunta2,
                onOptionSelected = { respuestaPregunta2 = "Tu" })
            RadioButtonOption(
                text = "Mi",
                selectedOption = respuestaPregunta2,
                onOptionSelected = { respuestaPregunta2 = "Mi" })
        }

        BotonVerificarRespuesta {
            verificarRespuestas()
        }
    }
}

@Composable
fun BotonVerificarRespuesta(onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth() // Asegura que el Row ocupe todo el ancho disponible
    ) {
        Button(
            border = BorderStroke(2.dp, Color(244, 225, 220)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(240, 150, 55), contentColor = Color.White
            ),
            onClick = { onClick() },
            modifier = Modifier
                //.align(Alignment.CenterHorizontally) // Centra el botón horizontalmente
        ) {
            Text("Verificar respuesta", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun RadioButtonOption(text: String, selectedOption: String, onOptionSelected: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedOption == text,
            onClick = onOptionSelected
        )
        Text(text)
    }
}

@Composable
fun Timer(seconds: Int, onTimeUp: () -> Unit, modifier: Modifier = Modifier) {
    var timer by remember { mutableIntStateOf(seconds) }

    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        } else {
            onTimeUp()
        }
    }

    Text(
        text = "Tiempo restante: $timer segundos",
        fontSize = 10.sp, color = Color.Red, modifier = modifier
    )
}

@Composable
fun ComposableButton(
    id: Int, onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = listOf(Color.Green, Color.Blue, Color.Yellow, Color.Magenta, Color.Gray)
    Button(
        shape = CircleShape,
        onClick = { onButtonClick(id) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
//            containerColor = Color.Transparent,
            contentColor = Color.Blue
        ),
        modifier = modifier
            .size(64.dp)
            .border(5.dp, colors[id - 1], CircleShape) // Remove border
    ) {
        Text(
            text = "$id", fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
//            color = Color.Blue
        )
    }
}
@Preview(showBackground = true, widthDp = 280)
@Composable
fun EvaluacionUsoComaPreview() {
    OrtografiaMariamelTheme {
        EvaluacionUsoComa(viewModel = viewModel(factory = AppViewModelProvider.Factory), {}, {})
    }
}
@Preview(showBackground = true)
@Composable
fun EvaluacionUsoDeZPreview() {
    OrtografiaMariamelTheme {
        EvaluacionUsoDeZ(viewModel = viewModel(factory = AppViewModelProvider.Factory), {}, {})
    }
}

@Preview(showBackground = true, widthDp = 280)
@Composable
fun EvaluacionUsoDeCPreview() {
    OrtografiaMariamelTheme {
        EvaluacionUsoDeC(viewModel = viewModel(factory = AppViewModelProvider.Factory), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun EvaluacionTildeDiacriticaPreview() {
    OrtografiaMariamelTheme {
        EvaluacionTildeDiacritica(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            {},
            {})
    }
}

@Preview(showBackground = true)
@Composable
fun EvaluacionScreenPreview() {
    OrtografiaMariamelTheme {
        EvaluacionScreen(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ }, onItemMenuButtonClicked = {})
    }
}
