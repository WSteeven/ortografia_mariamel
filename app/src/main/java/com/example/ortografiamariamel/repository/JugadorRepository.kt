package com.example.ortografiamariamel.repository

import com.example.ortografiamariamel.model.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {

      fun getJugador(): Flow<Jugador?>

      suspend fun setJugador(jugador: Jugador)

}