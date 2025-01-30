package com.york_ruve.quickbitemaneger.Domain.Model

data class Clients(
    val id: Int? = null,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String
)