package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients

interface IIngredientsRepository {
    suspend fun getAllIngredients(): List<Ingredients>
    suspend fun SubtractIngredientStock(ingredientId: Int?, quantity: Double?)
    suspend fun getCriticalIngredients(): List<Ingredients>
    suspend fun insertIngredient(ingredient: Ingredients)
    suspend fun getIngredientById(ingredientId: Int): Ingredients
    suspend fun updateIngredient(ingredient: Ingredients)
    suspend fun deleteIngredient(ingredient: Ingredients)
}