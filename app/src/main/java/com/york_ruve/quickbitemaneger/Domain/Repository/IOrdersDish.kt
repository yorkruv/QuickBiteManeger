package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish

interface IOrdersDish {
    suspend fun insertOrderDish(ordersDish: OrdersDish)
    suspend fun deleteOrderDish(ordersDish: OrdersDish)
}