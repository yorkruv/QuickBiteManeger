package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class ingredientsRepository @Inject constructor(private val ingredientsDao: ingredientsDao): IIngredientsRepository {
    override suspend fun getAllIngredients(): List<Ingredients> {
        return ingredientsDao.getAllIngredients().map {
            Ingredients(
                it.ingredientId,
                it.nombre,
                it.stock,
                it.unidad,
                it.criticalQuantity
            )
        }
    }

    override suspend fun SubtractIngredientStock(ingredientId: Int?, quantity: Double?) {
        ingredientsDao.updateIngredientStock(ingredientId, quantity)
    }

    override suspend fun insertIngredient(ingredient: Ingredients) {
        val Entity = ingredientEntity(ingredient.id,ingredient.name,ingredient.stock,ingredient.unit,ingredient.criticalQuantity)
        ingredientsDao.insertIngredient(Entity)
    }

    override suspend fun getIngredientById(ingredientId: Int): Ingredients {
        return ingredientsDao.getIngredientById(ingredientId).let {
            Ingredients(
                it.ingredientId,
                it.nombre,
                it.stock,
                it.unidad,
                it.criticalQuantity
            )
        }
    }
    override suspend fun updateIngredient(ingredient: Ingredients) {
        val Entity = ingredientEntity(ingredient.id,ingredient.name,ingredient.stock,ingredient.unit,ingredient.criticalQuantity)
        ingredientsDao.updateIngredient(Entity)
    }
}