package com.york_ruve.quickbitemaneger.Domain.Model

data class Ingredients (
    val id: Int? = null,
    val name: String,
    val stock: Double,
    val unit: String,
    val criticalQuantity: Double
)