package com.example.ortografiamariamel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ortografiamariamel.model.Jugador
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setJugador(jugador:Jugador)

    @Query("SELECT * FROM jugadores LIMIT 1")
     fun getJugador(): Flow<Jugador?>
}