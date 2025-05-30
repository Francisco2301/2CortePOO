package com.oscar.a2docorte.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.a2docorte.data.Estudiante
import com.oscar.a2docorte.data.EstudianteDatabase
import com.oscar.a2docorte.repository.EstudianteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EstudianteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EstudianteRepository

    val estudiantes: StateFlow<List<Estudiante>>

    init {
        val dao = EstudianteDatabase.getDatabase(application).estudianteDao()
        repository = EstudianteRepository(dao)
        estudiantes = repository.estudiantes
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    fun insertar(nombre: String, carrera: String, edad: Int) {
        viewModelScope.launch {
            try {
                repository.insertar(Estudiante(nombre = nombre, carrera = carrera, edad = edad))
                Log.d("EstudianteViewModel", "Estudiante insertado: $nombre")
            } catch (e: Exception) {
                Log.e("EstudianteViewModel", "Error al insertar estudiante", e)
            }
        }
    }
}