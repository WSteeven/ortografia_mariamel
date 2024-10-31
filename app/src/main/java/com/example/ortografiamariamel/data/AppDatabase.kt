package com.example.ortografiamariamel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ortografiamariamel.data.dao.JugadorDao
import com.example.ortografiamariamel.data.dao.NivelDao
import com.example.ortografiamariamel.data.dao.UnidadDao
import com.example.ortografiamariamel.model.Jugador
import com.example.ortografiamariamel.model.Nivel
import com.example.ortografiamariamel.model.Unidad

@Database(
    entities = [Unidad::class, Nivel::class, Jugador::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun unidadDao(): UnidadDao
    abstract fun nivelDao(): NivelDao
    abstract fun jugadorDao(): JugadorDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "ortografia_mariamel")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}