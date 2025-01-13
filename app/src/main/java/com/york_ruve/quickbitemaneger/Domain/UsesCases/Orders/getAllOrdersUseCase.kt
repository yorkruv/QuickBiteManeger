package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository

class getAllOrdersUseCase(private val ordersRepository: IOrdersRepository) {
    suspend operator fun invoke(): List<Orders> {
        return ordersRepository.getAllOrders()
    }
}