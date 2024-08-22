package com.example.ortografiamariamel.ui.screens.unidad1

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.data.Datasource
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun Actividad2U1(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {
    // Define snackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

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
                    text = "Seleccione el monosílabo en cada oración",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp,
                    color = Color(230, 170, 75),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )

                LadderScreen(viewModel, snackbarHostState)

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

    // Add SnackbarHost at the top level of your composable hierarchy
    SnackbarHost(hostState = snackbarHostState)
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LadderScreen(viewModel: AppViewModel, snackbarHostState: SnackbarHostState) {
    var selectedButton by remember { mutableIntStateOf(-1) } // -1 para indicar que ningún botón está seleccionado
    var showDialog by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    EnergyBar(viewModel.uiState.value.energiasJuego2U1, 7)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.juego_2_2),
            contentDescription = "Juego",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxHeight(.83f)
                .fillMaxWidth(.7f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.BottomCenter)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            ComposableButton(
                id = 7, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                }, modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 24.dp)
            )
            ComposableButton(id = 6, onButtonClick = {
                selectedButton = it
                showDialog = true
            }, modifier = Modifier.align(Alignment.CenterHorizontally))
            ComposableButton(
                id = 5, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
            )
            ComposableButton(id = 4, onButtonClick = {
                selectedButton = it
                showDialog = true
            }, modifier = Modifier.padding(top = 4.dp))
            ComposableButton(
                id = 3, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                }, modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 40.dp)
            )
            ComposableButton(id = 2, onButtonClick = {
                selectedButton = it
                showDialog = true
            }, modifier = Modifier.padding(bottom = 8.dp, start = 8.dp))
            Spacer(modifier = Modifier.padding(4.dp))
            ComposableButton(
                id = 1, onButtonClick = {
                    selectedButton = it
                    showDialog = true
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }

        // Mostrar el modal si showDialog es true
        if (showDialog) {
            MinimalDialog(
                viewModel = viewModel,
                index = selectedButton,
                onDismissRequest = {
                    showDialog = false
                },
                onVerification = { isCorrect ->
                    snackbarMessage = if (isCorrect) "¡Respuesta correcta!" else "Respuesta incorrecta"
                }
            )
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
fun ComposableButton(
    id: Int, onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = CircleShape,
        onClick = { onButtonClick(id) },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier
            .size(64.dp)
            .border(0.dp, Color.Transparent) // Remove border
    ) {
        Text("")
    }
}


@Composable
fun MenuDropdown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        TextField(
            value = selectedOption,
            onValueChange = { /* no-op */ },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
            enabled = false,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Localized description"
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)  // Notificar selección
                        expanded = false
                    },
                    text = { Text(option) },
                )
            }
        }
    }
}

@Composable
fun MinimalDialog(viewModel: AppViewModel, index: Int, onDismissRequest: () -> Unit, onVerification: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                when (index) {
                    1 -> ContenidoOracion1(viewModel = viewModel, onClose = onDismissRequest, onVerification)
                    2 -> ContenidoOracion2(viewModel = viewModel, onClose = onDismissRequest, onVerification)
                    3 -> ContenidoOracion3(viewModel = viewModel, onClose = onDismissRequest, onVerification)
                    4 -> ContenidoOracion4(viewModel = viewModel, onClose = onDismissRequest , onVerification)
                    5 -> ContenidoOracion5(viewModel = viewModel, onClose = onDismissRequest, onVerification )
                    6 -> ContenidoOracion6(viewModel = viewModel, onClose = onDismissRequest , onVerification)
                    7 -> ContenidoOracion7(viewModel = viewModel, onClose = onDismissRequest , onVerification)
                    else -> Log.d("ACTIVIDAD2-U1", "No hay pregunta para este boton $index")
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion1(viewModel: AppViewModel, onClose: () -> Unit,    onVerification: (Boolean) -> Unit // Agrega el callback
) {
    var selectedOption1 by remember { mutableStateOf("") }
    var selectedOption2 by remember { mutableStateOf("") }
    var selectedOption3 by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 3 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption1, selectedOption2, selectedOption3)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Text("Contesta correctamente la oración con las posibles respuestas")

    FlowRow(modifier = Modifier.fillMaxWidth()) {
        Text("Dice que ")
        MenuDropdown(options = listOf("el", "él"),
            selectedOption = selectedOption1,
            onOptionSelected = { selectedOption1 = it })
        Text(" libro es ")
        MenuDropdown(options = listOf("de", "dé"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it })
        Text(" ")
        MenuDropdown(options = listOf("el", "él"),
            selectedOption = selectedOption3,
            onOptionSelected = { selectedOption3 = it })
        Text(".")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion2(viewModel: AppViewModel, onClose: () -> Unit,  onVerification: (Boolean) -> Unit ) {
    var selectedOption by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 2 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Text("Contesta correctamente la oración con las posibles respuestas")
    FlowRow(modifier = Modifier.fillMaxWidth()) {
        MenuDropdown(options = listOf("Tú", "Tu"),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it })
        Text(" eres mi mejor amigo.")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = {
        checkResponse()
    }) {
        Text("Verificar respuesta")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion3(viewModel: AppViewModel, onClose: () -> Unit,  onVerification: (Boolean) -> Unit ) {
    var selectedOption1 by remember { mutableStateOf("") }
    var selectedOption2 by remember { mutableStateOf("") }
    var selectedOption3 by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 3 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption1, selectedOption2, selectedOption3)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Text("Contesta correctamente la oración con las posibles respuestas")

    FlowRow(modifier = Modifier.fillMaxWidth()) {
        Text("Dice que ")
        MenuDropdown(options = listOf("el", "él"),
            selectedOption = selectedOption1,
            onOptionSelected = { selectedOption1 = it })
        Text(" libro es ")
        MenuDropdown(options = listOf("de", "dé"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it })
        Text(" ")
        MenuDropdown(options = listOf("el", "él"),
            selectedOption = selectedOption3,
            onOptionSelected = { selectedOption3 = it })
        Text(".")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion4(viewModel: AppViewModel, onClose: () -> Unit, onVerification: (Boolean) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 4 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }

    Text("Contesta correctamente la oración con las posibles respuestas")

    FlowRow(modifier = Modifier.fillMaxWidth()) {
        MenuDropdown(options = listOf("El", "Él"),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it })
        Text(" me lo contó en la clase.")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion5(viewModel: AppViewModel, onClose: () -> Unit, onVerification: (Boolean) -> Unit) {
    var selectedOption1 by remember { mutableStateOf("") }
    var selectedOption2 by remember { mutableStateOf("") }
    var selectedOption3 by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 5 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption1, selectedOption2, selectedOption3)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Text("Contesta correctamente la oración con las posibles respuestas")
    FlowRow(modifier = Modifier.fillMaxWidth()) {
        Text("Ya ")
        MenuDropdown(options = listOf("se", "sé"),
            selectedOption = selectedOption1,
            onOptionSelected = { selectedOption1 = it })
        Text(" que no ")
        MenuDropdown(options = listOf("te", "té"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it })
        Text(" gusta el ")
        MenuDropdown(options = listOf("te", "té"),
            selectedOption = selectedOption3,
            onOptionSelected = { selectedOption3 = it })
        Text(".")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion6(viewModel: AppViewModel, onClose: () -> Unit, onVerification: (Boolean) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 6 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }

    Text("Contesta correctamente la oración con las posibles respuestas")

    FlowRow(modifier = Modifier.fillMaxWidth()) {
        MenuDropdown(options = listOf("Tu", "Tú"),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it })
        Text(" tienes siempre la razón.")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoOracion7(viewModel: AppViewModel, onClose: () -> Unit, onVerification: (Boolean) -> Unit) {
    var selectedOption1 by remember { mutableStateOf("") }
    var selectedOption2 by remember { mutableStateOf("") }
    val response = Datasource().responses.firstOrNull { it.index == 7 }

    //verificamos la respuesta
    fun checkResponse() {
        val respuestas = listOf(selectedOption1, selectedOption2)
        if (respuestas == response?.responses) {
            onVerification(true)
        } else {
            onVerification(false)
            viewModel.restarEnergiasJuego2U1()
        }
        onClose()
    }
    Text("Contesta correctamente la oración con las posibles respuestas")
    FlowRow(modifier = Modifier.fillMaxWidth()) {
        Text("Espero que ")
        MenuDropdown(options = listOf("se", "sé"),
            selectedOption = selectedOption1,
            onOptionSelected = { selectedOption1 = it })
        Text(" lo ")
        MenuDropdown(options = listOf("de", "dé"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it })
        Text(" a él.")

    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = { checkResponse() }) {
        Text("Verificar respuesta")
    }
}
@Composable
fun PlayerStatus(
    avatarResId: Int,
    level: Int,
    progress: Int,
    maxProgress: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = avatarResId),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "Nivel: $level",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            ProgressBar(progress, maxProgress, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun ProgressBar(progress: Int, maxProgress: Int, modifier: Modifier = Modifier) {
    val progressPercentage = (progress.toFloat() / maxProgress.toFloat()) * 100

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            .background(Color.LightGray, RoundedCornerShape(4.dp))
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progressPercentage / 100f)
                .background(Color.Green, RoundedCornerShape(4.dp))
        )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "${progressPercentage.toInt()}%",
        fontSize = 14.sp,
        color = Color.Black,
        textAlign = TextAlign.End,
        modifier = Modifier.fillMaxWidth()
    )
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
