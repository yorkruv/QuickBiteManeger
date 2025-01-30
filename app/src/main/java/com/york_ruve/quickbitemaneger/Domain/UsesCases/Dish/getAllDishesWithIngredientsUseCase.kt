package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class getAllDishesWithIngredientsUseCase @Inject constructor(private val dishRepository: IDishesRepository) {
    operator suspend fun invoke():List<DishWithIngredients>{
        return dishRepository.getAllDishesWithIngredients()
    }
}