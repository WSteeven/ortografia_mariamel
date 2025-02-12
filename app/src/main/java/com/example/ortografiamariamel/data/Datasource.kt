package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.model.Carta
import com.example.ortografiamariamel.model.Response

class Datasource {
    private val respuestas = listOf(
        Carta(id = 1, texto = "El"),
        Carta(id = 2, texto = "Tú"),
        Carta(id = 3, texto = "Mi"),
        Carta(id = 4, texto = "Té"),
    )

    private val definiciones = listOf(
        Carta(id = 1, texto = "Artículo"),
        Carta(id = 2, texto = "Pronombre Personal"),
        Carta(id = 3, texto = "Adjetivo"),
        Carta(id = 4, texto = "Sustantivo"),
    )

//    val niveles = listOf(
//        Nivel(1, "Nivel 1", true),
//        Nivel(2, "Nivel 2", false),
//        Nivel(3, "Nivel 3", false),
//    )

    fun loadRespuestas(): List<Carta> {
        return respuestas.shuffled()
    }

    fun loadDefiniciones(): List<Carta> {
        return definiciones.shuffled()
    }

     val responses = listOf(
        Response(1, listOf("Te", "el", "té")),
        Response(2, listOf("Tú")),
        Response(3, listOf("el", "de", "él")),
        Response(4, listOf("Él")),
        Response(5, listOf("sé", "te", "té")),
        Response(6, listOf("Tú")),
        Response(7, listOf("se", "dé")),
    )

}