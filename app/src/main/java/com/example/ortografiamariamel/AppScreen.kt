package com.example.ortografiamariamel


import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.navigation.AppNavHost
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

// Screens
enum class AppScreen(@StringRes val title: Int) {
    Inicio(R.string.inicio),
    DatosJugador(R.string.datos_jugador),
    Menu(R.string.menu),
    Unidad1(R.string.unidad1),
    Actividad1U1(R.string.actividad1),
    Actividad2U1(R.string.actividad2),
    Actividad3U1(R.string.actividad3),
    FinJuegoActividad1(R.string.actividad1),
    FinJuegoLoseActividad1(R.string.actividad1),
    MenuJuego1(R.string.niveles_unidad1),
    Unidad2(R.string.unidad2),
    MenuJuego2(R.string.niveles_unidad2),
    Actividad1U2(R.string.actividad1),
    Actividad2U2(R.string.actividad2),
    Actividad3U2(R.string.actividad3),
    Unidad3(R.string.unidad3),
    MenuJuego3(R.string.niveles_unidad3),
    Actividad1U3(R.string.actividad1),
    Actividad2U3(R.string.actividad2),
    Actividad3U3(R.string.actividad3),
    Unidad4(R.string.unidad4),
    Actividad4(R.string.actividad4),
}


@Composable
fun App(
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()){
    AppNavHost(viewModel=viewModel, navController = navController)
}


@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        App()
    }
}