package com.york_ruve.quickbitemaneger.Domain.Model

data class Orders(
    val id: Int? = null,
    val fecha: String,
    val cliente: String,
    val estado: String,
    val total: Double
)