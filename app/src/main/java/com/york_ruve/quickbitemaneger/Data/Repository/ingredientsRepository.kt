package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository

class ingredientsRepository(private val ingredientsDao: ingredientsDao): IIngredientsRepository {
    override suspend fun getAllIngredients(): List<Ingredients> {
        return ingredientsDao.getAllIngredients().map {
            Ingredients(
                it.ingredientId,
                it.nombre,
                it.stock,
                it.unidad
            )
        }
    }

    override suspend fun updateIngredientStock(ingredientId: Int, quantity: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun insertIngredient(ingredient: Ingredients) {
        val Entity = ingredientEntity(ingredient.id,ingredient.name,ingredient.stock,ingredient.unit)
        ingredientsDao.insertIngredient(Entity)
    }

    override suspend fun getIngredientById(ingredientId: Int): Ingredients {
        TODO("Not yet implemented")
    }
}