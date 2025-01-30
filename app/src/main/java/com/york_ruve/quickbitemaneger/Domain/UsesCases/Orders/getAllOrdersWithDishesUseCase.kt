package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class getAllOrdersWithDishesUseCase @Inject constructor(private val ordersRepository: IOrdersRepository) {
    suspend operator fun invoke(): List<orderWithDishes> {
        return ordersRepository.getOrdersWithDishes()
    }
}