package com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients

import com.york_ruve.quickbitemaneger.Data.Repository.dishIngredientsRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import javax.inject.Inject

class deleteDishIngredientsByDishIdUseCase @Inject constructor(private val dishIngredientsRepository: IDishIngredients) {
    operator fun invoke(dishId: Int) {
        dishIngredientsRepository.deleteDishIngredientsByDishId(dishId)
    }

}