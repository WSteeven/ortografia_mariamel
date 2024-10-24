package com.example.ortografiamariamel.repository

import com.example.ortografiamariamel.data.dao.JugadorDao
import com.example.ortografiamariamel.model.Jugador
import kotlinx.coroutines.flow.Flow

class OfflineJugadorRepository(private val jugadorDao: JugadorDao): JugadorRepository {

    override fun getJugador(): Flow<Jugador?> =jugadorDao.getJugador()

    override suspend fun setJugador(jugador: Jugador)=jugadorDao.setJugador(jugador)
}