package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import javax.inject.Inject

class ordersRepository @Inject constructor(private val ordersDao: ordersDao) : IOrdersRepository {
    override suspend fun getAllOrders(): List<Orders> {
        return ordersDao.getAllOrders().map {
            Orders(it.orderId, it.fecha, it.cliente, it.estado, it.total)
        }
    }

    override suspend fun getOrdersWithDishes(): List<orderWithDishes> {
        return ordersDao.getAllOrdersWithDishes()
    }

    override suspend fun getOrderById(id: Int): Orders {
        return ordersDao.getOrderById(id).let {
            Orders(it.orderId, it.fecha, it.cliente, it.estado, it.total)
        }
    }

    override suspend fun getOrdersWithDishesById(id: Int): orderWithDishes {
        return ordersDao.getOrdersWithDishesById(id)
    }


    override suspend fun getOrdersByState(state: String): List<Orders> {
        return ordersDao.getOrdersByState(state).map {
            Orders(it.orderId, it.fecha, it.cliente, it.estado, it.total)
        }
    }


    override suspend fun insertOrders(orders: Orders) {
        val ordersEntity =
            ordersEntity(orders.id, orders.fecha, orders.cliente, orders.estado, orders.total)
        ordersDao.insertOrder(ordersEntity)
    }

    override suspend fun updateOrders(orders: Orders) {
        val ordersEntity =
            ordersEntity(orders.id, orders.fecha, orders.cliente, orders.estado, orders.total)
        ordersDao.updateOrder(ordersEntity)
    }

    override suspend fun deleteOrders(orders: Orders) {
        val ordersEntity =
            ordersEntity(orders.id, orders.fecha, orders.cliente, orders.estado, orders.total)
        ordersDao.deleteOrder(ordersEntity)
    }
}