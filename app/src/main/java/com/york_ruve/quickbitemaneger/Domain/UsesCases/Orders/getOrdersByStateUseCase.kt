package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class getOrdersByStateUseCase @Inject constructor(private val ordersRepository: IOrdersRepository) {
    suspend operator fun invoke(state: String): List<orderWithDishes> {
        return ordersRepository.getOrdersByState(state)

    }
}