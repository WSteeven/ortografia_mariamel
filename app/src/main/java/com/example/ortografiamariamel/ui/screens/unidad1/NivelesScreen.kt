package com.example.ortografiamariamel.ui.screens.unidad1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun Niveles1(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onClick: () -> Unit = {},
) {
    val firebase = FirebaseRepository(LocalContext.current)
    firebase.habilitarJuegosSegunProgreso()
    val localNombre = firebase.leerNombreLocalmente()
    var mostrarInstrucciones by remember { mutableStateOf(false) }
    val soundManager = SoundManager(LocalContext.current)
    var juego1Desbloqueado by remember { mutableStateOf(false) }
    var juego2Desbloqueado by remember { mutableStateOf(false) }
//    var juego3Desbloqueado by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    // Add SnackbarHost at the top level of your composable hierarchy
    SnackbarHost(hostState = snackbarHostState)
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    LaunchedEffect(localNombre) {
        firebase.verificarJuegoCompletado(1, 1) { juego1Desbloqueado = it }
        firebase.verificarJuegoCompletado(1, 2) { juego2Desbloqueado = it }
//        firebase.verificarJuegoCompletado(1, 3) { juego3Desbloqueado = it }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = R.drawable.patio_colegio),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding)
//                .background(Color.Green)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End,
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.4f)
                    ) {
//                    LottieAnimationInstrucciones()
                        InstruccionesMarquee(mostrarInstrucciones) {
                            mostrarInstrucciones = !mostrarInstrucciones
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f), // Ocupa el 60% de la altura
                    verticalArrangement = Arrangement.SpaceBetween, // Espacio uniforme entre botones
                    horizontalAlignment = Alignment.CenterHorizontally // Alinear botones al centro
                ) {
                    NivelJuego(
                        isUnlocked = juego2Desbloqueado, descripcion = "Juego 3",
                        onClick = {
                            viewModel.setPantallaJuego(AppScreen.Actividad3U1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        },
                        onClickBloqueado = {
//                            Log.d("ACTIVIDAD2-U1", "Click en el boton bloqueado $it")
                            snackbarMessage = "Juego Bloqueado, debes completar el juego anterior primero para desbloquear este juego"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.End)
                            .padding(start = 32.dp)
                    )
                    NivelJuego(
                        isUnlocked = juego1Desbloqueado,
                        descripcion = "Juego 2", onClick = {
                            viewModel.setPantallaJuego(AppScreen.Actividad2U1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        },
                        onClickBloqueado = { snackbarMessage = "Juego Bloqueado, debes completar el juego anterior primero para desbloquear este juego" },
                        modifier = Modifier
                            .align(Alignment.End)
                            .fillMaxWidth()
                            .padding(start = 200.dp)
                    )
                    NivelJuego(
                        isUnlocked = true, descripcion = "Juego 1", onClick = {
                            viewModel.setPantallaJuego(AppScreen.Actividad1U1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                            .padding(start = 32.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(.3f))
            }
        }
    }
}

@Composable
fun NivelJuego(
    isUnlocked: Boolean,
    onClick: () -> Unit,
    descripcion: String,
    modifier: Modifier = Modifier,
    onClickBloqueado: (Boolean) -> Unit = {}
) {
    Box(
        modifier = modifier.then(
            if (isUnlocked) Modifier.clickable(onClick = {
                onClick()
            })
            else Modifier.clickable { onClickBloqueado(true) }
        )
    ) {
        Column {
            // Aquí cambiamos la imagen dependiendo si está desbloqueado o no
            Image(
                painter = painterResource(id = if (isUnlocked) R.drawable.lock_02_green else R.drawable.lock),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Text(
                descripcion,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InstruccionesMarquee(mostrarInstrucciones: Boolean, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 20.dp)
//                .background(Color.White)
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(color = Color.White)) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Información"
            )
            Text("INSTRUCCIONES", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        }

    }

// Marquee only animates when the content doesn't fit in the max width.
    Box(modifier = Modifier.fillMaxSize()) {
        if (mostrarInstrucciones) {

            Image(
                painter = painterResource(id = R.drawable.nube),
                contentDescription = "nube instrucciones",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 112.dp)
                    .padding(horizontal = 20.dp)
//                .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.instrucciones_1),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.basicMarquee()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.instrucciones_2),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.basicMarquee()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.instrucciones_3),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.basicMarquee()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NivelesScreenPreview() {
    OrtografiaMariamelTheme {
        Niveles1(viewModel = viewModel(factory = AppViewModelProvider.Factory))
    }
}
