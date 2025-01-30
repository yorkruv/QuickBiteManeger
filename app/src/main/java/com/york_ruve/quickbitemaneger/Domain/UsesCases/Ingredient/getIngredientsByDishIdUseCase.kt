package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class getIngredientsByDishIdUseCase @Inject constructor(private val ingredientsRepository: IDishesRepository) {
    operator suspend fun invoke(dishId: Int) = ingredientsRepository.getIngredientsByDishId(dishId)
}