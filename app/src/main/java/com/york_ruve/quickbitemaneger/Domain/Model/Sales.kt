package com.york_ruve.quickbitemaneger.Domain.Model

data class Sales(
    val id: Int? = null,
    val id_pedido: Int,
    val cliente: String?,
    val fecha: String,
    val total: Double,
)