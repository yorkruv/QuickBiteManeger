package com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish

import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository

class getAllDishesUseCase(private val dishRepository:IDishesRepository) {
    operator suspend fun invoke():List<Dish>{
        return dishRepository.getAllDishes()
    }
}