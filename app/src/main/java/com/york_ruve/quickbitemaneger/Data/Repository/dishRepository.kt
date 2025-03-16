package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class dishRepository @Inject constructor(
    private val dishDao: dishesDao,
) : IDishesRepository {
    override suspend fun getAllDishes(): List<Dish> {
        return dishDao.getAllDishes().map {
            Dish(
                it.dishId,
                it.nombre,
                it.descripcion,
                it.precio
            )
        }
    }

    override suspend fun getAllDishesWithIngredients(): List<DishWithIngredients> {
        return dishDao.getAllDishesWithIngredients()
    }

    override suspend fun getIngredientsWithQuantity(dishId: Int): List<IngredientsWithQuantity> {
        return dishDao.getIngredientsWithQuantity(dishId)
    }

    override suspend fun getIngredientsByDishId(dishId: Int): DishWithIngredients? {
        return dishDao.getIngredientsByDishId(dishId)
    }

    override suspend fun insertDish(dish: Dish): Long {
        val dishEntity = dishEntity(dish.id, dish.name, dish.description, dish.price)
        return dishDao.insertDish(dishEntity)
    }
    override suspend fun deleteDish(dish: Dish) {
        val dishEntity = dishEntity(dish.id, dish.name, dish.description, dish.price)
        dishDao.deleteDish(dishEntity)
    }

}