package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.dishIngredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import javax.inject.Inject

class dishIngredientsRepository @Inject constructor(private val dishIngredientsDao: dishIngredientsDao) :IDishIngredients {
    override fun getDishIngredientsById(dishId: Int?, ingredientId: List<Int?>): List<DishIngredient> {
        return dishIngredientsDao.getDishIngredientsById(dishId, ingredientId)
    }

    override fun insertDishIngredient(dishIngredient: DishIngredient) {
        dishIngredientsDao.insertDishIngredient(dishIngredient)
    }

    override fun deleteDishIngredient(dishIngredient: DishIngredient) {
        dishIngredientsDao.deleteDishIngredient(dishIngredient)
    }

    override fun deleteDishIngredientsByDishId(dishId: Int) {
        dishIngredientsDao.deleteDishIngredientsByDishId(dishId)
    }

    override fun updateDishIngredientQuantity(dishId: Int, ingredientId: Int, newQuantity: Double) {
        dishIngredientsDao.updateDishIngredientQuantity(dishId, ingredientId, newQuantity)
    }
}