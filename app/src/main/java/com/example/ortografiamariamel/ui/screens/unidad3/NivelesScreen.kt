package com.example.ortografiamariamel.ui.screens.unidad3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.unidad1.InstruccionesMarquee
import com.example.ortografiamariamel.ui.screens.unidad1.NivelJuego
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun Niveles3(
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
    var juego3U2Desbloqueado by remember { mutableStateOf(false) }
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
        firebase.verificarJuegoCompletado(2, 3) {juego3U2Desbloqueado = it }
        firebase.verificarJuegoCompletado(3, 1) { juego1Desbloqueado = it }
        firebase.verificarJuegoCompletado(3, 2) { juego2Desbloqueado = it }

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
                .fillMaxSize().padding(padding)
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
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                ) {
                    InstruccionesMarquee(mostrarInstrucciones) {mostrarInstrucciones=!mostrarInstrucciones}
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f), // Ocupa el 60% de la altura
                verticalArrangement = Arrangement.SpaceBetween, // Espacio uniforme entre botones
//                horizontalAlignment = Alignment.CenterHorizontally // Alinear botones al centro
            ) {
                NivelJuego(
                    isUnlocked = juego2Desbloqueado, descripcion = "Juego 3",
                    onClick = {
                        viewModel.setPantallaJuegoU3(AppScreen.Actividad3U3)
                        soundManager.playSound(R.raw.correct_card_sound)
                        onClick()
                    },
                    onClickBloqueado = {
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
                        viewModel.setPantallaJuegoU3(AppScreen.Actividad2U3)
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
                    isUnlocked = juego3U2Desbloqueado, descripcion = "Juego 1", onClick = {
                        viewModel.setPantallaJuegoU3(AppScreen.Actividad1U3)
                        soundManager.playSound(R.raw.correct_card_sound)
                        onClick()
                    },
                    onClickBloqueado = { snackbarMessage = "Aún no has completado todos los juegos de la UNIDAD 2. Por favor completalos para desbloquear este juego"},
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

@Preview(showBackground = true)
@Composable
fun Niveles3ScreenPreview() {
    OrtografiaMariamelTheme {
        Niveles3(viewModel = viewModel(factory = AppViewModelProvider.Factory))
    }
}
