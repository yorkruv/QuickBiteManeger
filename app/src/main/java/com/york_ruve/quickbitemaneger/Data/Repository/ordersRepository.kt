package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository

class ordersRepository(private val ordersDao: ordersDao) : IOrdersRepository {
    override suspend fun getAllOrders(): List<Orders> {
        return ordersDao.getAllOrders().map {
            Orders(it.id,it.fecha,it.id_cliente,it.estado,it.total)
        }
    }

    override suspend fun getOrdersByState(state: String): List<Orders> {
        return ordersDao.getOrdersByState(state).map {
            Orders(it.id,it.fecha,it.id_cliente,it.estado,it.total)
        }
    }

    override suspend fun insertOrders(orders: Orders) {
        val ordersEntity = ordersEntity(orders.id,orders.fecha,orders.id_cliente,orders.estado,orders.total)
        ordersDao.insertOrder(ordersEntity)
    }

    override suspend fun updateOrders(orders: Orders) {
        val ordersEntity = ordersEntity(orders.id,orders.fecha,orders.id_cliente,orders.estado,orders.total)
        ordersDao.updateOrder(ordersEntity)
    }

    override suspend fun deleteOrders(orders: Orders) {
        val ordersEntity = ordersEntity(orders.id,orders.fecha,orders.id_cliente,orders.estado,orders.total)
        ordersDao.deleteOrder(ordersEntity)
    }
}