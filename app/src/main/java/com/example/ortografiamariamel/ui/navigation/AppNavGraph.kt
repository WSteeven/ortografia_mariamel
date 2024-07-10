package com.example.ortografiamariamel.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.views.unidad1.Actividad1
import com.example.ortografiamariamel.ui.views.unidad1.ActividadIDestination
import com.example.ortografiamariamel.ui.views.DatosJugadorDestination
import com.example.ortografiamariamel.ui.views.DatosJugadorScreen
import com.example.ortografiamariamel.ui.views.InicioDestination
import com.example.ortografiamariamel.ui.views.InicioScreen
import com.example.ortografiamariamel.ui.views.MenuDestination
import com.example.ortografiamariamel.ui.views.MenuScreen
import com.example.ortografiamariamel.ui.views.unidad1.UnidadI
import com.example.ortografiamariamel.ui.views.unidad1.UnidadIDestination

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = InicioDestination.route,
        modifier = modifier
    ) {
        // pantalla de inicio
        composable(route = InicioDestination.route) {
            InicioScreen(
                onNextButtonClicked = { navController.navigate(DatosJugadorDestination.route) },
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        // 2da pantalla
        composable(route = DatosJugadorDestination.route) {
            DatosJugadorScreen(
                onNextButtonClicked = {
                    navController.navigate(MenuDestination.route)
                },
                modifier = Modifier
            )
        }
        // 3ra pantalla
        composable(route = MenuDestination.route) {
            MenuScreen(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(UnidadIDestination.route)
                },
                modifier = Modifier
            )
        }
        // 4ta pantalla - UNIDAD I
        composable(route = UnidadIDestination.route) {
            UnidadI(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(ActividadIDestination.route)
                },
                modifier = Modifier
            )
        }
        // 5ta pantalla - ACTIVIDAD UNIDAD I
        composable(route = ActividadIDestination.route) {
            Actividad1(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigateUp()
                },
                modifier = Modifier
            )
        }
        // 6ta pantalla - UNIDAD II
        composable(route = AppScreen.Unidad2.name) {
            UnidadI(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(uiState.menu.name)
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
        AppNavHost(navController = rememberNavController())
    }
}
