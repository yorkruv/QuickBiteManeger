package com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients

import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import javax.inject.Inject

class getDishIngredientsByIdUseCase @Inject constructor(private val dishIngredientsRepository: IDishIngredients) {
    operator suspend fun invoke(dishId: Int?,ingredientId: List<Int?>): List<DishIngredient> {
        return dishIngredientsRepository.getDishIngredientsById(dishId,ingredientId)
    }
}