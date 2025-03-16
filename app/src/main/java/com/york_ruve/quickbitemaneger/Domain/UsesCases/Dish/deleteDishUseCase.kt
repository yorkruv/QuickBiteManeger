package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class deleteDishUseCase @Inject constructor(private val dishRepository: IDishesRepository) {
    suspend operator fun invoke(dish:Dish) {
        dishRepository.deleteDish(dish)
    }
}