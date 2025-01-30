package com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish

import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersDish
import javax.inject.Inject

class deleteOrderDishUseCase @Inject constructor(private val ordersDishRepository: IOrdersDish) {
    operator suspend fun invoke(ordersDish: OrdersDish) {
        ordersDishRepository.deleteOrderDish(ordersDish)
    }
}