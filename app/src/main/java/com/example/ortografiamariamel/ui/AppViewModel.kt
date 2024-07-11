package com.example.ortografiamariamel.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.ui.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

//    Configure all functions for setValues, calculates, etc.
    fun setNombreJugador(nombre: String){
        _uiState.update { currentState->
            currentState.copy(
                nombreJugador = nombre,
            )
        }
    }
    fun setEdad(edad: Int){
        _uiState.update { currentState->
            currentState.copy(
                edad = edad,
            )
        }
    }
    fun setPantallaActual(unidad: AppScreen){
        Log.d("AppViewModel","setPantallaActual: $unidad")
        _uiState.update { currentState->
            currentState.copy(
                menu = unidad,
            )
        }
    }


    fun setEnergiasDisponibles(valor:Int){
        _uiState.update { currentState->
            currentState.copy(
                energias = valor,
            )
        }
    }
    fun setPuntaje(valor:Int){
        _uiState.update { currentState->
            currentState.copy(
                puntaje = valor,
            )
        }
    }

}