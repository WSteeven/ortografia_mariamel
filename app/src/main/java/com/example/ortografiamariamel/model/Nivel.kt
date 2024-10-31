package com.example.ortografiamariamel.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "niveles",
    foreignKeys = [ForeignKey(
        entity = Unidad::class,
        parentColumns = ["id"],
        childColumns = ["unidadId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Nivel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val unidadId: Int,
    val esDesbloqueado: Boolean,
    val estaJugado: Boolean

)