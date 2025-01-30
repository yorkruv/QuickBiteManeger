package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
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

    override suspend fun getIngredientsByDishId(dishId: Int): DishWithIngredients? {
        return dishDao.getIngredientsByDishId(dishId)
    }

    override suspend fun insertDish(dish: Dish) {
        val dishEntity = dishEntity(dish.id, dish.name, dish.description, dish.price)
        dishDao.insertDish(dishEntity)
    }
}