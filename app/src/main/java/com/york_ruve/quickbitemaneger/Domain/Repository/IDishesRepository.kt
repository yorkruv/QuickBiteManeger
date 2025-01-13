package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredientCrossRef
import com.york_ruve.quickbitemaneger.Domain.Model.Dish

interface IDishesRepository {
    suspend fun getAllDishes(): List<Dish>
    suspend fun getIngredientsByDishId(dishId: Int): List<DishIngredientCrossRef>
    suspend fun insertDish(dish: Dish)
    suspend fun insertDishWithIngredientsCrossRef(crossRef: DishIngredientCrossRef)
    suspend fun sellDish(dishId: Int, quantity: Int)
    suspend fun insertDishWithIngredients(dish: Dish, ingredientsQuantity: List<Pair<Int, Double>>)
}