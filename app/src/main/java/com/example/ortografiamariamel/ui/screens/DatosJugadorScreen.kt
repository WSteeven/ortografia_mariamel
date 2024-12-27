package com.example.ortografiamariamel.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatosJugadorScreen(
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    viewModel: JugadorViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
//    var playerName by remember { mutableStateOf(viewModel.uiState.value.nombreJugador) }
    val uiState = viewModel.uiState.collectAsState().value
    var playerName = uiState.nombreJugador

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val firebase = FirebaseRepository(LocalContext.current)
    val localNombre = firebase.leerNombreLocalmente()
    var showDialog by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(localNombre) {
        if(localNombre!=null && playerName!=localNombre){
            viewModel.setNombreJugador(localNombre)
        }
    }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopBar(
                title = AppScreen.DatosJugador.title,
                puedeNavegarAtras = true,
                navigateUp = onPrevButtonClicked,
                modifier = modifier,
                mostrarEncabezado = true,
                mostrarMenu = false
            )
        }
    ) { innerPadding ->
        if(localNombre!=null){
//        if (localNombre == null) {
//            playerName = localNombre
//            viewModel.setNombreJugador(playerName)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                ContinuousSlideAnimation(
                    modifier = Modifier
                        .fillMaxSize(.5f)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Bienvenido $playerName")
                    IconButton(onClick = { showDialog=true }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onNextButtonClicked()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    border = BorderStroke(4.dp, Color(244, 225, 220)),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(240, 150, 55), contentColor = Color.White
                    ),
                ) {
                    Text(text = stringResource(R.string.siguiente), fontSize = 30.sp)
                }
                if (showDialog) {
                    EditarNombre(
                        nombreAntiguo = playerName,
                        viewModel = viewModel,
                        firebase = firebase,
                        onDismissRequest = {
                            showDialog = false
                            snackbarMessage = "¡Se ha modificado el nombre del jugador!"
                        },
                        onModificacion = {
                            val nuevoNombre = firebase.leerNombreLocalmente()
                            playerName = nuevoNombre?:it
                            viewModel.setNombreJugador(playerName)
                            }
                    )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                ContinuousSlideAnimation(
                    modifier = Modifier
                        .fillMaxSize(.5f)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = playerName,
                    onValueChange = { playerName = it },
                    singleLine = true,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        autoCorrect = true,
                        imeAction = ImeAction.Done
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    enabled = playerName.isNotEmpty(),
                    onClick = {
//                    viewModel.updateUiState(Jugador(nombre = playerName))
//                    coroutineScope.launch { viewModel.setJugador() }
                        viewModel.setNombreJugador(playerName)
                        firebase.guardarNombreToFirebase(playerName)
                        firebase.guardarNombreLocalmente(playerName)
//                    viewModel.setEdad(sliderValue)
                        onNextButtonClicked()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    border = BorderStroke(4.dp, Color(244, 225, 220)),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(240, 150, 55), contentColor = Color.White
                    ),
                ) {
                    Text(text = stringResource(R.string.siguiente), fontSize = 30.sp)
                }
            }
        }
        LaunchedEffect(snackbarMessage) {
            snackbarMessage?.let {
                snackbarHostState.showSnackbar(it)
                snackbarMessage = null
            }
        }
    }
}

@Composable
fun EditarNombre(
    firebase: FirebaseRepository,
    nombreAntiguo: String,
    viewModel: AppViewModel,
    onDismissRequest: () -> Unit,
    onModificacion: (String) -> Unit
) {
    var nuevoNombre by remember { mutableStateOf(nombreAntiguo) }
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(4.dp)) {
                Text("Modificar Nombre", fontWeight = FontWeight.Bold)
                TextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    singleLine = true,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        autoCorrect = true,
                        imeAction = ImeAction.Done
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    enabled = nuevoNombre.isNotEmpty(),
                    onClick = {
                        viewModel.setNombreJugador(nuevoNombre)
                        firebase.guardarNombreToFirebase(nuevoNombre)
                        firebase.guardarNombreLocalmente(nuevoNombre)
                        onModificacion(nuevoNombre)

                        onDismissRequest()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    border = BorderStroke(4.dp, Color(244, 225, 220)),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(240, 150, 55), contentColor = Color.White
                    ),
                ) {
                    Text(text = "Guardar", fontSize = 30.sp)
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatosJugadorPreview() {
    OrtografiaMariamelTheme {
        DatosJugadorScreen(onNextButtonClicked = {}, onPrevButtonClicked = {}, modifier = Modifier)
    }
}

