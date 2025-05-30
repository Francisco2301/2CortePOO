package com.oscar.a2docorte.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Estudiante::class], version = 1, exportSchema = false)
abstract class EstudianteDatabase : RoomDatabase() {

    abstract fun estudianteDao(): EstudianteDao

    companion object {
        @Volatile
        private var INSTANCE: EstudianteDatabase? = null

        fun getDatabase(context: Context): EstudianteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EstudianteDatabase::class.java,
                    "EstudianteDatabase" // <- Aquí das nombre a la BD
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}