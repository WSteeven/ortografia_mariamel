package com.example.ortografiamariamel.data

import android.content.Context
import com.example.ortografiamariamel.repository.JugadorRepository
import com.example.ortografiamariamel.repository.OfflineJugadorRepository

interface AppContainer {
    val nivelesRepository: NivelRepository
    val jugadorRepository: JugadorRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val nivelesRepository: NivelRepository by lazy {
        OfflineNivelRepository(AppDatabase.getDatabase(context).nivelDao())
    }
    override val jugadorRepository: JugadorRepository by lazy {
        OfflineJugadorRepository(
            AppDatabase.getDatabase(context).jugadorDao()
        )
    }
}