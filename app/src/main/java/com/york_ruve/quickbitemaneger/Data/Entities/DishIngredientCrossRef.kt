package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "dish_ingredient_cross_ref",
    primaryKeys = ["dishId", "ingredientId"],
    foreignKeys = [
        ForeignKey(
            entity = dishEntity::class,
            parentColumns = ["dishId"],
            childColumns = ["dishId"]
        ),
        ForeignKey(
            entity = ingredientEntity::class,
            parentColumns = ["ingredientId"],
            childColumns = ["ingredientId"]
        )
    ],
    indices = [
        Index("dishId"),
        Index("ingredientId")
    ]
)
data class DishIngredientCrossRef(
    val dishId: Int,
    val ingredientId: Int,
    val quantity: Double
)