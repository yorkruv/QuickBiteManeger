package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class updateOrdersUseCase @Inject constructor(private val ordersRepository: IOrdersRepository) {
    suspend operator fun invoke(orders: Orders) {
        ordersRepository.updateOrders(orders)
    }
}