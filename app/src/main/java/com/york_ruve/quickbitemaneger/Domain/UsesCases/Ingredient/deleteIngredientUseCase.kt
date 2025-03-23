package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class deleteIngredientUseCase @Inject constructor(private val ingredientsRepository: IIngredientsRepository){
    operator suspend fun invoke(ingredients: Ingredients){
        ingredientsRepository.deleteIngredient(ingredients)
    }
}