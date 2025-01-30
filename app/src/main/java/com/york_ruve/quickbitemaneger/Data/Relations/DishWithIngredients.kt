package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

data class DishWithIngredients (
    @Embedded
    val dish: dishEntity,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "ingredientId",
        associateBy = Junction(DishIngredient::class)
    )
    val ingredients: List<ingredientEntity>
)