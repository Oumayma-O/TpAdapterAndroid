package com.example.adaptertp

enum class Matieres {
    COURS,
    TP
}

data class Student(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val subject: Matieres
)
