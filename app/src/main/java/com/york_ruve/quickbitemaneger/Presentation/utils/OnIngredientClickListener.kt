package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients

interface OnIngredientClickListener {
    fun onEditIngredientClick(ingredient: Ingredients)
    fun onDeleteIngredientClick(ingredientId: Int)
}