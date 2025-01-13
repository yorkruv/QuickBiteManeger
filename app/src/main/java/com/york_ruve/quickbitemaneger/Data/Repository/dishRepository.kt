package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredientCrossRef
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository

class dishRepository(
    private val dishDao: dishesDao,
    private val ingredientsDao: ingredientsDao
) : IDishesRepository {
    override suspend fun getAllDishes(): List<Dish> {
        return dishDao.getAllDishes().map {
            Dish(
                it.dish.dishId,
                it.dish.nombre,
                it.dish.descripcion,
                it.dish.precio,
            )
        }
    }

    override suspend fun getIngredientsByDishId(dishId: Int): List<DishIngredientCrossRef> {
        return dishDao.getIngredientsByDishId(dishId)
    }

    override suspend fun insertDish(dish: Dish) {
        val dishEntity = dishEntity(dish.id, dish.name, dish.description, dish.price)
        dishDao.insertDish(dishEntity)
    }

    override suspend fun insertDishWithIngredientsCrossRef(crossRef: DishIngredientCrossRef) {
        dishDao.insertDishWithIngredients(crossRef)
    }

    override suspend fun sellDish(dishId: Int, quantity: Int) {
        val dishIngredients = dishDao.getIngredientsByDishId(dishId)

        dishIngredients.forEach {
            val ingredient = ingredientsDao.getIngredientById(it.ingredientId)
            if (ingredient.stock < it.quantity){
                throw IllegalArgumentException("Not enough ingredients in stock")
            }
        }
        dishIngredients.forEach {
            val ingredient = ingredientsDao.getIngredientById(it.ingredientId)
            ingredientsDao.updateIngredientStock(ingredient.ingredientId, it.quantity)
        }
    }

    override suspend fun insertDishWithIngredients(
        dish: Dish,
        ingredientsQuantity: List<Pair<Int, Double>>
    ) {
        insertDish(dish)
        ingredientsQuantity.forEach {
            val crossRef = DishIngredientCrossRef(dish.id, it.first, it.second)
            insertDishWithIngredientsCrossRef(crossRef)
        }
    }

}