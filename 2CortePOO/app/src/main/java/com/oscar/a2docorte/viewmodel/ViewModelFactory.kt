package com.oscar.a2docorte.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EstudianteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EstudianteViewModel::class.java)) {
            return EstudianteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}