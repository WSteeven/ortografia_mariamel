package com.example.ortografiamariamel.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.DatosJugadorScreen
import com.example.ortografiamariamel.ui.screens.InicioScreen
import com.example.ortografiamariamel.ui.screens.MenuScreen
import com.example.ortografiamariamel.ui.screens.unidad1.Actividad1
import com.example.ortografiamariamel.ui.screens.unidad1.Actividad2U1
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoLoseUnidad1
import com.example.ortografiamariamel.ui.screens.unidad1.FinJuegoUnidad1
import com.example.ortografiamariamel.ui.screens.unidad1.Niveles1
import com.example.ortografiamariamel.ui.screens.unidad1.UnidadI
import com.example.ortografiamariamel.ui.screens.unidad2.Actividad2
import com.example.ortografiamariamel.ui.screens.unidad2.UnidadII
import com.example.ortografiamariamel.ui.screens.unidad3.Actividad3
import com.example.ortografiamariamel.ui.screens.unidad3.UnidadIII
import com.example.ortografiamariamel.ui.screens.unidad4.Actividad4
import com.example.ortografiamariamel.ui.screens.unidad4.UnidadIV
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AppNavHost(
    viewModel: AppViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Inicio.name,
        modifier = modifier
    ) {
        // pantalla de inicio
        composable(route = AppScreen.Inicio.name) {
            InicioScreen(
                onNextButtonClicked = {
                    navController.navigate(AppScreen.DatosJugador.name)
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        // 2da pantalla
        composable(route = AppScreen.DatosJugador.name) {
            DatosJugadorScreen(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Menu.name)
                },
                modifier = Modifier
            )
        }
        // 3ra pantalla
        composable(route = AppScreen.Menu.name) {
            MenuScreen(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigate(AppScreen.DatosJugador.name)
                },
                onNextButtonClicked = {
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d(
//                        "AppNavGraph",
//                        "oibc composable Menu: ${uiState.menu.name} - ${viewModel.uiState.value.menu.name}"
//                    )
                    navController.navigate(viewModel.uiState.value.menu.name)
//                    Log.d("AppNavGraph","oibc composable Menu after: ${uiState.menu.name} - ${viewModel.uiState.value.menu.name}")
                },
                modifier = Modifier
            )
        }
        // 4ta pantalla - UNIDAD I
        composable(route = AppScreen.Unidad1.name) {
            UnidadI(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.MenuJuego1.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable UnidadI: ${uiState.menu.name}")
//                    navController.navigate(uiState.menu.name)
                    navController.navigate(viewModel.uiState.value.menu.name)
//                    Log.d("AppNavGraph", "composable UnidadI after: ${uiState.menu.name}")
                },
                modifier = Modifier
            )
        }
        // 5ta pantalla - ACTIVIDAD UNIDAD I
        composable(route = AppScreen.Actividad1U1.name) {
            Actividad1(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(viewModel.uiState.value.screenEndGame.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        composable(route = AppScreen.Actividad2U1.name) {
            Actividad2U1(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(viewModel.uiState.value.screenEndGame.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        composable(route = AppScreen.FinJuegoActividad1.name) {
            FinJuegoUnidad1(
                viewModel = viewModel,
                onItemMenuButtonClicked = {
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                onClick = { navController.navigate(AppScreen.MenuJuego1.name) }
            )
        }
        composable(route = AppScreen.FinJuegoLoseActividad1.name) {
            FinJuegoLoseUnidad1(
                viewModel = viewModel,
                onItemMenuButtonClicked = {
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.MenuJuego1.name)
                },
            )
        }
        composable(route = AppScreen.MenuJuego1.name) {
            Niveles1(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(viewModel.uiState.value.menuJuego.name)
                })
        }
        // 6ta pantalla - UNIDAD II
        composable(route = AppScreen.Unidad2.name) {
            UnidadII(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Actividad2.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable UnidadII: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        // 7ma pantalla - ACTIVIDAD II
        composable(route = AppScreen.Actividad2.name) {
            Actividad2(
                viewModel = viewModel,
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        // 8ava pantalla - UNIDAD III
        composable(route = AppScreen.Unidad3.name) {
            UnidadIII(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Actividad3.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable UnidadIII: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        // 9na pantalla - ACTIVIDAD III
        composable(route = AppScreen.Actividad3.name) {
            Actividad3(
                viewModel = viewModel,
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        // 10ma pantalla - UNIDAD IV
        composable(route = AppScreen.Unidad4.name) {
            UnidadIV(
                viewModel = viewModel,
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Actividad4.name)
                },
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable UnidadIV: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
        // 11ava pantalla - ACTIVIDAD IV
        composable(route = AppScreen.Actividad4.name) {
            Actividad4(
                viewModel = viewModel,
                onItemMenuButtonClicked = {
//                    Log.d("AppNavGraph", "composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(viewModel.uiState.value.menu.name)
                },
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        AppNavHost(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            navController = rememberNavController()
        )
    }
}
