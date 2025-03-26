package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class getAlldishesWithQuantityUseCase @Inject constructor(private val ordersRepository: IOrdersRepository) {
    operator suspend fun invoke(orderId: Int): List<dishWithQuantity> {
        return ordersRepository.getAlldishesWithQuantity(orderId)
    }

}