package com.example.ortografiamariamel.ui.data

import com.example.ortografiamariamel.AppScreen

data class AppUiState(
    val nombreJugador: String = "",
    val edad: Int = 8,
    val menu:AppScreen = AppScreen.Unidad1
)