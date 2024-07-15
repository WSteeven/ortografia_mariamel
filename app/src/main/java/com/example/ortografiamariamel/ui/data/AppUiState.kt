package com.example.ortografiamariamel.ui.data

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.example.ortografiamariamel.AppScreen

data class AppUiState(
    val nombreJugador: String = "",
    val edad: Int = 8,
    val menu: AppScreen = AppScreen.Unidad1,
    val menu_juego: AppScreen = AppScreen.Actividad1,
    val energias: Int = 5,
    val puntaje: Int = 0,

    val drawer: DrawerValue = DrawerValue.Closed,
    val drawerState: DrawerState = DrawerState(initialValue = drawer)
)