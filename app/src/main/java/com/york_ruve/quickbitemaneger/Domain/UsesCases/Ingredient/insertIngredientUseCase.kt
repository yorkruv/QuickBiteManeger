package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository

class insertIngredientUseCase(private val ingredientRepository: IIngredientsRepository) {
    operator suspend fun invoke(ingredients: Ingredients){
        ingredientRepository.insertIngredient(ingredients)
    }
}