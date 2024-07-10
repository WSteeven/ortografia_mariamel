package com.example.ortografiamariamel.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.ortografiamariamel.model.Carta

class Datasource {
    private val respuestas = listOf<Carta>(
        Carta(id = 1, texto = "El"),
        Carta(id = 2, texto = "Tú"),
        Carta(id = 3, texto = "Mi"),
        Carta(id = 4, texto = "Té"),
    )

    private val definiciones = listOf<Carta>(
        Carta(id = 1, texto = "Artículo"),
        Carta(id = 2, texto = "Pronombre Personal"),
        Carta(id = 3, texto = "Adjetivo"),
        Carta(id = 4, texto = "Sustantivo"),
    )

    fun loadRespuestas(): List<Carta> {
        return respuestas.shuffled()
    }

    fun loadDefiniciones(): List<Carta> {
        return definiciones.shuffled()
    }
}