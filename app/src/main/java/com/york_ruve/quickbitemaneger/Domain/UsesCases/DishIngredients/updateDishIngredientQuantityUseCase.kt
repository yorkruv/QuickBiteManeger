package com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients

import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import javax.inject.Inject

class updateDishIngredientQuantityUseCase @Inject constructor(private val dishIngredientsRepository: IDishIngredients) {
    suspend operator fun invoke(dishId: Int, ingredientId: Int, newQuantity: Double) {
        dishIngredientsRepository.updateDishIngredientQuantity(dishId, ingredientId, newQuantity)
    }
}