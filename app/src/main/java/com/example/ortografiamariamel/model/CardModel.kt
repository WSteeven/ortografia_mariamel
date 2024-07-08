package com.example.ortografiamariamel.model

data class CardModel(
    val id: Int,
    val number: Int,
    var isSelected: Boolean = false,
    var isMatched: Boolean = false
)