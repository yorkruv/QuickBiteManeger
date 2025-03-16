package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient

interface IDishIngredients {
    fun getDishIngredientsById(dishId: Int?, ingredientId: List<Int?>): List<DishIngredient>
    fun insertDishIngredient(dishIngredient: DishIngredient)
    fun deleteDishIngredient(dishIngredient: DishIngredient)
    fun deleteDishIngredientsByDishId(dishId: Int)
    fun updateDishIngredientQuantity(dishId: Int, ingredientId: Int, newQuantity: Double)
}