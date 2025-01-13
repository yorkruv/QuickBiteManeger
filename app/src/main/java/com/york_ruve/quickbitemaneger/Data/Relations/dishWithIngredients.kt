package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredientCrossRef
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

data class dishWithIngredients(
    @Embedded val dish: dishEntity,
    @Relation(
        entity = ingredientEntity::class,
        parentColumn = "dishId",
        entityColumn = "ingredientId",
        associateBy = Junction(
            value = DishIngredientCrossRef::class,
            parentColumn = "dishId",
            entityColumn = "ingredientId"
        )
    )
    val ingredients: List<ingredientEntity>
)