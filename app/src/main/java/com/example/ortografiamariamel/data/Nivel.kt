package com.example.ortografiamariamel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "niveles")
data class Nivel (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val nombre: String,
    val esDesbloqueado:Boolean,
    val estaJugado:Boolean

)