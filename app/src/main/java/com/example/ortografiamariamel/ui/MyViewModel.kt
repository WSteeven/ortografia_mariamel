package com.example.ortografiamariamel.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ortografiamariamel.data.AppDatabase
import com.example.ortografiamariamel.data.dao.NivelDao
import com.example.ortografiamariamel.data.dao.UnidadDao
import com.example.ortografiamariamel.model.Nivel
import com.example.ortografiamariamel.model.Unidad
import kotlinx.coroutines.launch


suspend fun populateDatabase(unidadDao: UnidadDao, nivelDao: NivelDao) {
    //Define las unidades
    val unidades = listOf(
        Unidad(nombre = "Unidad 1", bloqueada = false),
        Unidad(nombre = "Unidad 2", bloqueada = true),
        Unidad(nombre = "Unidad 3", bloqueada = true),
        Unidad(nombre = "Unidad 4", bloqueada = true)
    )
    // Inserta las unidades
    unidades.forEach { unidad ->
        unidadDao.insertUnidad(unidad)
        for (i in 1..3){
            nivelDao.insertNivel(Nivel(nombre="Nivel $i", esDesbloqueado = false, unidadId = unidad.id, estaJugado = false))
        }
    }
}
class MyViewModel(application: Application): AndroidViewModel(application){
    private val unidadDao: UnidadDao = AppDatabase.getDatabase(application).unidadDao()
    private val nivelDao: NivelDao = AppDatabase.getDatabase(application).nivelDao()

    init {
        viewModelScope.launch { populateDatabase(unidadDao, nivelDao) }
    }
}