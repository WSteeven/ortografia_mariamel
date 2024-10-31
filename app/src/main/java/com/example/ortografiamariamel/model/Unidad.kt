package com.example.ortografiamariamel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("unidades")
class Unidad (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val nombre: String,
    val bloqueada:Boolean =true
)