package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders

interface IOrdersRepository {
    suspend fun getAllOrders(): List<Orders>
    suspend fun getOrdersWithDishes(): List<orderWithDishes>
    suspend fun getAlldishesWithQuantity(orderId: Int): List<dishWithQuantity>
    suspend fun getOrderById(id: Int): Orders
    suspend fun getOrdersWithDishesById(id: Int): orderWithDishes
    suspend fun getOrdersByState(state: String): List<orderWithDishes>
    suspend fun insertOrders(orders: Orders): Long
    suspend fun updateOrders(orders: Orders)
    suspend fun deleteOrders(orders: Orders)
}