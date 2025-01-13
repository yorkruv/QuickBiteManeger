package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Domain.Model.Orders

interface IOrdersRepository {
    suspend fun getAllOrders(): List<Orders>
    suspend fun getOrdersByState(state: String): List<Orders>
    suspend fun insertOrders(orders: Orders)
    suspend fun updateOrders(orders: Orders)
    suspend fun deleteOrders(orders: Orders)
}