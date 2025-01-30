package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "dishIngredient",
    primaryKeys = ["dishId", "ingredientId"],
    indices = [
        Index("dishId"),
        Index("ingredientId")
    ]
)
data class DishIngredient(
    val dishId: Int,
    val ingredientId: Int,
    val quantity: Double
)