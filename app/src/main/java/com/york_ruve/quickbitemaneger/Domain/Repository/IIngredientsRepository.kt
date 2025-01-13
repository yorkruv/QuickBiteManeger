package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients

interface IIngredientsRepository {
    suspend fun getAllIngredients(): List<Ingredients>
    suspend fun updateIngredientStock(ingredientId: Int, quantity: Double)
    suspend fun insertIngredient(ingredient: Ingredients)
    suspend fun getIngredientById(ingredientId: Int): Ingredients
}