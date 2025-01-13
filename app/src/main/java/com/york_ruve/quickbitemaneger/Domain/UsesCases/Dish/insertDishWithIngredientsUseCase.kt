package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Data.Repository.dishRepository
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository

class insertDishWithIngredientsUseCase(private val dishRepository: IDishesRepository) {
    suspend operator fun invoke(dish: Dish, ingredientsQuantity: List<Pair<Int, Double>>) {
        dishRepository.insertDishWithIngredients(dish, ingredientsQuantity)
    }

}