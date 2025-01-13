package com.york_ruve.quickbitemaneger.Domain.Model

import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
)