package com.example.ortografiamariamel.ui.screens.unidad3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun Niveles3(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onClick: () -> Unit = {},
) {
    val soundManager = SoundManager(LocalContext.current)
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
//                .background(Color.Green)
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
//                    LottieAnimationInstrucciones()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f), // Ocupa el 60% de la altura
                verticalArrangement = Arrangement.SpaceBetween, // Espacio uniforme entre botones
//                horizontalAlignment = Alignment.CenterHorizontally // Alinear botones al centro
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(end = 54.dp)
                        .clickable(onClick = {
                            //Aquí se setea la pantalla de navegacion
                            viewModel.setPantallaJuegoU3(AppScreen.Actividad3U3)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Text("Nivel 3")//, modifier=Modifier.padding(top=8.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .padding(bottom = 50.dp)
                        .clickable(onClick = {
                            //Aquí se setea la pantalla de navegacion
                            viewModel.setPantallaJuegoU3(AppScreen.Actividad2U3)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Text("Nivel 2")
                    }
                }
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp)
                        .clickable(onClick = {
                            //Aquí se setea la pantalla de navegacion
                            viewModel.setPantallaJuegoU3(AppScreen.Actividad1U3)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.lock_02_green),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Text("Nivel 1")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(.3f))
        }
    }
}


//@Composable
//fun LottieAnimationInstrucciones() {
//    // Cargar la animación desde assets
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.instrucciones))
//
//    // Controlar la animación
//    val animatable = rememberLottieAnimatable()
//
//    LaunchedEffect(composition) {
//        animatable.animate(composition)
//    }
//
//    LottieAnimation(
//        composition = composition,
//        iterations = LottieConstants.IterateForever,
//        modifier = Modifier.fillMaxSize()
//    )
//}

@Preview(showBackground = true)
@Composable
fun Niveles3ScreenPreview() {
    OrtografiaMariamelTheme {
        Niveles3(viewModel = viewModel(factory = AppViewModelProvider.Factory))
    }
}