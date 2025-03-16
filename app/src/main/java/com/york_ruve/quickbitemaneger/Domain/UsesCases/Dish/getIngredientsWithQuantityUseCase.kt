package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class getIngredientsWithQuantityUseCase @Inject constructor(private val dishRepository: IDishesRepository) {
    suspend operator fun invoke(dishId: Int): List<IngredientsWithQuantity> {
        return dishRepository.getIngredientsWithQuantity(dishId)
    }

}