package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.model.Nivel
import kotlinx.coroutines.flow.Flow

interface NivelRepository {
    fun getAllNivelesStream(): Flow<List<Nivel>>

    fun getNivelStream(id: Int): Flow<Nivel?>

    suspend fun insertNivel(nivel:Nivel)

    suspend fun deleteNivel(nivel: Nivel)

    suspend fun updateNivel(nivel: Nivel)

}