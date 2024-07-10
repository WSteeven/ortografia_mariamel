package com.example.ortografiamariamel.model

data class Carta(val id: Int,
                 val texto:String,
                 var isSelected: Boolean = false,
                 var isMatched: Boolean = false,
                 var imageClicked: Boolean = false // Nuevo estado para manejar clic de mostrar/ocultar contenido
)
