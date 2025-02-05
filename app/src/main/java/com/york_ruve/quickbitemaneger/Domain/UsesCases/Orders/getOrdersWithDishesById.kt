package com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders

import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class getOrdersWithDishesById @Inject constructor(private val ordersRepository: IOrdersRepository) {
    operator suspend fun invoke(id: Int) = ordersRepository.getOrdersWithDishesById(id)
}