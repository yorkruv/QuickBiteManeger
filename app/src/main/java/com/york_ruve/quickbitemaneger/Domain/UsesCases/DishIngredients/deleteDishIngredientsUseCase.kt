package com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients

import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import javax.inject.Inject

class deleteDishIngredientsUseCase @Inject constructor(private val dishIngredientsRepository: IDishIngredients){
    operator fun invoke(dishIngredient: DishIngredient){
        dishIngredientsRepository.deleteDishIngredient(dishIngredient)
    }

}