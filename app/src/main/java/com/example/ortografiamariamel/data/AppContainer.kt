package com.example.ortografiamariamel.data

import android.content.Context

interface  AppContainer{
    val nivelesRepository: NivelRepository
}
class AppDataContainer(private val context: Context):AppContainer {
    override val nivelesRepository: NivelRepository by lazy {
        OfflineNivelRepository(AppDatabase.getDatabase(context).nivelDao())
    }
}