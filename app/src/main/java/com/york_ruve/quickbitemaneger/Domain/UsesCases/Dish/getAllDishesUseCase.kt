package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import javax.inject.Inject

class getAllDishesUseCase @Inject constructor(private val dishRepository:IDishesRepository) {
    operator suspend fun invoke():List<Dish>{
        return dishRepository.getAllDishes()
    }
}