package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class insertIngredientUseCase @Inject constructor(private val ingredientRepository: IIngredientsRepository) {
    operator suspend fun invoke(ingredients: Ingredients):Long{
        return ingredientRepository.insertIngredient(ingredients)
    }
}