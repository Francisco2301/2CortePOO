package com.oscar.a2docorte.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EstudianteDao {
    @Insert
    suspend fun insertar(estudiante: Estudiante)

    @Query("SELECT * FROM estudiante")
    fun obtenerTodos(): Flow<List<Estudiante>>
}
