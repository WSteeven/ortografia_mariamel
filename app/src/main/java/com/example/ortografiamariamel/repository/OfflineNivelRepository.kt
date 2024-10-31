package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.data.dao.NivelDao
import com.example.ortografiamariamel.model.Nivel
import kotlinx.coroutines.flow.Flow

class OfflineNivelRepository(private val nivelDao: NivelDao): NivelRepository {
    override fun getAllNivelesStream(): Flow<List<Nivel>> =nivelDao.getAllNiveles()

    override fun getNivelStream(id: Int): Flow<Nivel?> = nivelDao.getNivel(id)

    override suspend fun insertNivel(nivel: Nivel) = nivelDao.insertNivel(nivel)

    override suspend fun deleteNivel(nivel: Nivel) = nivelDao.delete(nivel)

    override suspend fun updateNivel(nivel: Nivel) = nivelDao.update(nivel)
}