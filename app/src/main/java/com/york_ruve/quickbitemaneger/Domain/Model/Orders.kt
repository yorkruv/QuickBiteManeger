package com.york_ruve.quickbitemaneger.Domain.Model

data class Orders(
    val id: Int,
    val fecha: String,
    val id_cliente: Int,
    val estado: String,
    val total: Double
)