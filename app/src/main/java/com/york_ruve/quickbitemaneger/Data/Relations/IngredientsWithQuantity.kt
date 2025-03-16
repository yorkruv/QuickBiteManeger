package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

data class IngredientsWithQuantity(
    @Embedded
    val ingredient: ingredientEntity,
    val quantity: Double
)
