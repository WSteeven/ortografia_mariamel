package com.example.ortografiamariamel.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ortografiamariamel.model.Nivel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@Dao
interface NivelDao {

    @Query("SELECT * from niveles ORDER BY nombre ASC")
    fun getAllNiveles(): Flow<List<Nivel>>

    @Query("SELECT * from niveles WHERE id=:id")
    fun getNivel(id:Int):Flow<Nivel>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNivel(nivel: Nivel)

    @Query("SELECT * FROM niveles WHERE unidadId = :unidadId")
    suspend fun getNivelesPorUnidad(unidadId: Long): List<Nivel>
    @Update
    suspend fun update(nivel:Nivel)

    @Delete
    suspend fun delete(nivel: Nivel)
}