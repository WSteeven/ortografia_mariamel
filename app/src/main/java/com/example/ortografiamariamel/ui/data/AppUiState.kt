package com.example.ortografiamariamel.ui.data

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import com.example.ortografiamariamel.AppScreen
import com.google.common.reflect.Reflection

data class AppUiState(
    val nombreJugador: String = "",
    val edad: Int = 8,
    val menu: AppScreen = AppScreen.Unidad1,
    val energias: Int = 5,
    val puntaje: Int = 0,

    val drawer: DrawerValue = DrawerValue.Closed,
    val drawerState: DrawerState = DrawerState(initialValue = drawer)
)