package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Model.Dish

interface IDishesRepository {
    suspend fun getAllDishes(): List<Dish>
    suspend fun getAllDishesWithIngredients(): List<DishWithIngredients>
    suspend fun getIngredientsWithQuantity(dishId: Int): List<IngredientsWithQuantity>
    suspend fun getIngredientsByDishId(dishId: Int): DishWithIngredients?
    suspend fun insertDish(dish: Dish):Long
    suspend fun deleteDish(dish: Dish)
}