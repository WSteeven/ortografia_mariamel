package com.example.ortografiamariamel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("jugadores")
class Jugador (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val nombre: String="",
    val edad: Int=8,
    val puntos:Int=0
)