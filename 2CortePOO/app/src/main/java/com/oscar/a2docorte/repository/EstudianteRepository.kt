package com.oscar.a2docorte.repository


import com.oscar.a2docorte.data.Estudiante
import com.oscar.a2docorte.data.EstudianteDao
import kotlinx.coroutines.flow.Flow

class EstudianteRepository(private val dao: EstudianteDao) {
    val estudiantes: Flow<List<Estudiante>> = dao.obtenerTodos()

    suspend fun insertar(estudiante: Estudiante) {
        dao.insertar(estudiante)
    }
}