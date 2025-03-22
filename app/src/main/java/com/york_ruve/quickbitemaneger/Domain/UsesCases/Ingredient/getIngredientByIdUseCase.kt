package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class getIngredientByIdUseCase @Inject constructor(private val ingredientRepository: IIngredientsRepository) {
    suspend operator fun invoke(ingredientId: Int) = ingredientRepository.getIngredientById(ingredientId)
}