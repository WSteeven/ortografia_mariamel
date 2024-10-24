package com.example.ortografiamariamel.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ortografiamariamel.model.Jugador
import com.example.ortografiamariamel.repository.JugadorRepository
import kotlinx.coroutines.launch

class JugadorViewModel(private val jugadorRepository: JugadorRepository) : ViewModel(){
    var jugadorUiState by mutableStateOf(JugadorUiState())
    private set

    init {
        viewModelScope.launch {
            // Cargar el jugador existente al inicializar el ViewModel
            jugadorRepository.getJugador().collect{jugador -> jugadorUiState = JugadorUiState(jugador?:Jugador())}
        }
    }
    fun updateUiState(jugador:Jugador){
        jugadorUiState = JugadorUiState(jugador)
    }

    suspend fun setJugador(){
            jugadorRepository.setJugador(jugadorUiState.jugador)
    }
}

data class JugadorUiState(val jugador: Jugador= Jugador())


