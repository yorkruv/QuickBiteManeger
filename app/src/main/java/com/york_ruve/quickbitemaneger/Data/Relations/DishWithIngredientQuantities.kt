package com.york_ruve.quickbitemaneger.Data.Relations

import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity

data class DishWithIngredientQuantities(
    val dish: dishEntity,
    val ingredientQuantities: List<IngredientQuantity>
)