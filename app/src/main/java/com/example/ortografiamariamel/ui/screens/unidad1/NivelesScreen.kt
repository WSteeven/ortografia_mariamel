package com.example.ortografiamariamel.ui.screens.unidad1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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

//@Composable
//fun LevelCard(level: Nivel, onClick: () -> Unit) {
//    val backgroundColor = if (level.esDesbloquado) Color.Green else Color.Gray
//    Box(
//        modifier = Modifier
//            .padding(8.dp)
//            .size(100.dp)
//            .background(backgroundColor)
//            .clickable(enabled = level.esDesbloquado, onClick = onClick),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = level.nombre, color = Color.White)
//    }
//}

//@Composable
//fun LevelGrid(levels: List<Nivel>, onLevelSelected: (Nivel) -> Unit) {
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(1),
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(levels) { level ->
//            LevelCard(level = level) {
//                onLevelSelected(level)
//            }
//        }
//
//    }
//}


@Composable
fun Niveles1(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onClick: () -> Unit = {},
) {
    val soundManager = SoundManager(LocalContext.current)
//    val niveles = listOf(
//        Nivel(1, "Nivel 1", true),
//        Nivel(2, "Nivel 2", false),
//        Nivel(3, "Nivel 3", false),
//    )
//    val levels by remember { mutableStateOf(Datasource().niveles) }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.fondo_pantalla_nivel),
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
            Spacer(modifier = Modifier.weight(.54f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f), // Ocupa el 60% de la altura
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
                            viewModel.setPantallaJuego(AppScreen.Actividad1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                    )
//                    Text("Boton 3")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .padding(bottom = 152.dp)
                        .clickable(onClick = {
                            //Aquí se setea la pantalla de navegacion
                            viewModel.setPantallaJuego(AppScreen.Actividad1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                    )
//                    Text("Boton 2")
                }
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .clickable(onClick = {
                            //Aquí se setea la pantalla de navegacion
                            viewModel.setPantallaJuego(AppScreen.Actividad1)
                            soundManager.playSound(R.raw.correct_card_sound)
                            onClick()
                        })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
//                    Text("Boton 1")
                }
            }
            Spacer(modifier = Modifier.weight(.3f))
        }
//        LevelGrid(levels = levels) { selectedLevel ->
//            // Lógica para iniciar el nivel seleccionado
//            if (selectedLevel.esDesbloquado) {
//                //Navegar al nivel seleccionado
//                navigateToLevel(selectedLevel)
//            } else {
//                showLevelLockedMessage(context)
//            }
//        }
    }
}

//private fun navigateToLevel(level: Nivel) {
    // Aquí podrías usar un NavController para navegar a la pantalla del nivel
    // Por ejemplo, si estás usando Jetpack Navigation:
    // navController.navigate("level/${level.id}")
//    println("Navegando al ${level.nombre}") // Simulación de navegación
//    LevelScreen(level = level)
//    println("Estás en ${level.nombre}")
//}

//@Composable
//fun LevelScreen(level: Nivel) {
//    // Lógica y UI del nivel
//    Text(text = "Estás en ${level.nombre}", style = MaterialTheme.typography.h4)
//    // Aquí puedes agregar la lógica del juego
//}


//private fun showLevelLockedMessage(context: Context) {
    // Podrías mostrar un diálogo o un Toast
//    Toast.makeText(context, "Este nivel está bloqueado.", Toast.LENGTH_SHORT).show()
//}

@Preview(showBackground = true)
@Composable
fun NivelesScreenPreview() {
    OrtografiaMariamelTheme {
        Niveles1(viewModel = viewModel(factory = AppViewModelProvider.Factory))
    }
}
