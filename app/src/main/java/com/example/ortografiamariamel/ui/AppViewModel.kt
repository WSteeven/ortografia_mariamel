package com.example.ortografiamariamel.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.ui.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()



    //    Configure all functions for setValues, calculates, etc.
    fun setNombreJugador(nombre: String) {
        _uiState.update { currentState ->
            currentState.copy(
                nombreJugador = nombre,
            )
        }
    }

    fun setEdad(edad: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                edad = edad,
            )
        }
    }

    fun setPantallaActual(unidad: AppScreen) {
        _uiState.update { currentState ->
            currentState.copy(
                menu = unidad,
            )
        }
//        Log.d("AppViewModel","valor despues de setear: ${_uiState.value.menu}")
    }

    fun setPantallaJuego(pantalla: AppScreen) {
        _uiState.update { currentState -> currentState.copy(menuJuego = pantalla) }
    }

    fun setPantallaJuegoU2(pantalla: AppScreen){
        _uiState.update { currentState ->currentState.copy(menuJuegoU2 = pantalla) }
    }
    fun setPantallaJuegoU3(pantalla: AppScreen){
        _uiState.update { currentState ->currentState.copy(menuJuegoU3 = pantalla) }
    }
    fun setPantallaJuegoU4(pantalla: AppScreen){
        _uiState.update { currentState ->currentState.copy(menuJuegoU4 = pantalla) }
    }

    /**
     * Set the Screen for end game, in case game is win before drain all energies, the Screen is
     * FinJuegoActividad1, else FinJuegoLoseActividad1
     */
    fun setScreenEndGame(pantalla: AppScreen) {
        _uiState.update { currentState -> currentState.copy(screenEndGame = pantalla) }
    }

    fun reestablecerEnergias() {
        _uiState.update { it -> it.copy(energias = 5) }
    }

    fun reestablecerAciertos() {
        _uiState.update { it -> it.copy(aciertos = 0) }
    }

    fun closeMenuLateral() {
        _uiState.update { currentState ->
            currentState.copy(drawer = DrawerValue.Closed)
        }
    }

    fun restarEnergia() {
        _uiState.update { currentState ->
            currentState.copy(
                energias = currentState.energias - 1,
            )
        }
    }
    fun restarEnergiasJuego2U1(){
        _uiState.update { currentState -> currentState.copy(energiasJuego2U1 =  currentState.energiasJuego2U1-1) }
    }

    fun sumarAciertos() {
        _uiState.update { currentState ->
            currentState.copy(
                aciertos = currentState.aciertos + 1,
            )
        }
    }

}