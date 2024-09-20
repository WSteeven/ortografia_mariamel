package com.example.ortografiamariamel.ui.data

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.example.ortografiamariamel.AppScreen

data class AppUiState(
    val nombreJugador: String = "",
    val edad: Int = 8,
    val menu: AppScreen = AppScreen.Unidad1,
    val screenEndGame: AppScreen = AppScreen.FinJuegoActividad1,
    val menuJuego: AppScreen = AppScreen.Actividad1U1,
    val menuJuegoU2: AppScreen = AppScreen.Actividad1U2,
    val menuJuegoU3: AppScreen = AppScreen.Actividad1U3,
    val energias: Int = 5,
    val energiasJuego2U1: Int = 7,
    val aciertos: Int = 0,

    val drawer: DrawerValue = DrawerValue.Closed,
    val drawerState: DrawerState = DrawerState(initialValue = drawer)
)