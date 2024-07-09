package com.example.ortografiamariamel.data

data class Carta(val id: Int,
                 val texto:String,
                 val number: Int,
                 var isSelected: Boolean = false,
                 var isMatched: Boolean = false)
