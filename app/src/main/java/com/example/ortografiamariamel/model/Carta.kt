package com.example.ortografiamariamel.model

data class Carta(val id: Int,
                 val texto:String,
                 var isSelected: Boolean = false,
                 var isMatched: Boolean = false,
                 var imageClicked: Boolean = false // Nuevo estado para manejar clic de mostrar/ocultar contenido
)

data class Question(
    val index: Int,
    val question: String,
    var isCorrrect: Boolean, // Índice de la respuesta correcta
    val respuestas: Int = 1
)

data class Response(
    val index: Int,
    val responses: List<String>
)

