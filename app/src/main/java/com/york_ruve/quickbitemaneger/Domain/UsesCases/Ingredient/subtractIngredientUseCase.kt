package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class subtractIngredientUseCase @Inject constructor(private val IingredientsRepository: IIngredientsRepository) {
    operator suspend fun invoke(ingredientId: Int?, quantity: Double?){
        IingredientsRepository.SubtractIngredientStock(ingredientId, quantity)
    }
}