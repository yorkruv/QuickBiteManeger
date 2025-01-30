package com.york_ruve.quickbitemaneger.Domain.Model

data class Sales(
    val id: Int? = null,
    val id_pedido: Int,
    val id_cliente: Int,
    val fecha: Long,
    val total: Double,
)