package com.example.ortografiamariamel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ortografiamariamel.model.Unidad

@Dao
interface UnidadDao {
    @Insert
    suspend fun insertUnidad(unidad:Unidad)

    @Query("SELECT * FROM unidades")
    suspend fun getUnidades(): List<Unidad>
}