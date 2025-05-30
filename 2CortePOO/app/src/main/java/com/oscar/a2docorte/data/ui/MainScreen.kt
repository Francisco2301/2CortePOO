package com.oscar.a2docorte.data.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.a2docorte.viewmodel.EstudianteViewModel


@Composable
fun MainScreen(viewModel: EstudianteViewModel) {
    var nombre by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    val estudiantes by viewModel.estudiantes.collectAsState()

    var errorNombre by remember { mutableStateOf("") }
    var errorCarrera by remember { mutableStateOf("") }
    var errorEdad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Centra verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it; errorNombre = "" },
            label = { Text("Nombre") },
            isError = errorNombre.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        if (errorNombre.isNotEmpty())
            Text(errorNombre, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = carrera,
            onValueChange = { carrera = it; errorCarrera = "" },
            label = { Text("Carrera") },
            isError = errorCarrera.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        if (errorCarrera.isNotEmpty())
            Text(errorCarrera, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it; errorEdad = "" },
            label = { Text("Edad") },
            isError = errorEdad.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        if (errorEdad.isNotEmpty())
            Text(errorEdad, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Validaciones
            val nombreValido = nombre.isNotBlank() && nombre.matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
            val carreraValida = carrera.isNotBlank() && carrera.matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
            val edadInt = edad.toIntOrNull()
            val edadValida = edadInt != null && edadInt in 15..100

            errorNombre = if (!nombreValido) "Solo letras y espacios, sin dejar vacío." else ""
            errorCarrera = if (!carreraValida) "Solo letras y espacios, sin dejar vacío." else ""
            errorEdad = if (!edadValida) "Edad debe ser entre 15 y 100." else ""

            if (nombreValido && carreraValida && edadValida) {
                viewModel.insertar(nombre.trim(), carrera.trim(), edadInt!!)
                nombre = ""; carrera = ""; edad = ""
            }
        }) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Estudiantes Registrados:", style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            items(estudiantes) { estudiante ->
                Text(
                    "${estudiante.nombre}, ${estudiante.carrera}, ${estudiante.edad} años",
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
