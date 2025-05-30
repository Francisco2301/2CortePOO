package com.oscar.a2docorte.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estudiante(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val carrera: String,
    val edad: Int
)
