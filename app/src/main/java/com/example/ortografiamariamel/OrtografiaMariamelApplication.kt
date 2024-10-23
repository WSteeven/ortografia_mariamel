package com.example.ortografiamariamel

import android.app.Application
import com.example.ortografiamariamel.data.AppContainer
import com.example.ortografiamariamel.data.AppDataContainer

class OrtografiaMariamelApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}