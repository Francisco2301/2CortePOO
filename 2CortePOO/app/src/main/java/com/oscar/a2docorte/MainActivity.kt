package com.oscar.a2docorte


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.a2docorte.viewmodel.EstudianteViewModel
import com.oscar.a2docorte.data.ui.MainScreen
import com.oscar.a2docorte.viewmodel.EstudianteViewModelFactory



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: EstudianteViewModel = viewModel(
                factory = EstudianteViewModelFactory(application)
            )
            MainScreen(viewModel)
        }
    }
}