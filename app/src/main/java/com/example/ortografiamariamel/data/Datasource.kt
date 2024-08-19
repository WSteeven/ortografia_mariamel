package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.model.Carta
import com.example.ortografiamariamel.model.Nivel
import com.example.ortografiamariamel.model.Question
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

    var questions = listOf(
        Question(1, "__ espero para tomar __ __", false, 3),
//    Question("Te/té espero para tomar el/él té/te", listOf("París", "Londres", "Berlín"), 0),
//              __                      __    __
        Question(2, "__ eres mi mejor amigo", false, 1),
//                    Tú/tu
//                    __
        Question(3, "Dice que __ libro es __ __.", false, 3),
//                             el/él  de/dé   el/él
//                             __     __         __
        Question(4, "__ me lo contó en la clase.", false, 1),
//                    El/Él
//                       __
        Question(5, "Ya __ que no __ gusta el __.", false, 3),
//                      se/sé      te/té    te/té
//                         __      __          __
        Question(6, "__ tienes siempre la razón.", false, 1),
//                    Tú/tu
//                    __
        Question(7, "Espero que __ lo __ a él.", false, 2),
//                            se/sé    dé/de
//                            __       __
    )
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