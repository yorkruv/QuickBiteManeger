package com.york_ruve.quickbitemaneger.Domain.Model

import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity

data class Orders(
    val id: Int? = null,
    val fecha: String,
    val id_cliente: Int,
    val estado: String,
    val total: Double
)