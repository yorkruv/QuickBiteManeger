package com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient

import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import javax.inject.Inject

class getCriticalIngredientsUseCase @Inject constructor(private val ingredientRepository: IIngredientsRepository) {
        operator suspend fun invoke():List<Ingredients>{
            return ingredientRepository.getCriticalIngredients()
        }
}