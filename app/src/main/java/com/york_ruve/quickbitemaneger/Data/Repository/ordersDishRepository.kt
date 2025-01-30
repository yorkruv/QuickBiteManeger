package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.ordersDishDao
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersDish
import javax.inject.Inject

class ordersDishRepository @Inject constructor(private val ordersDishDao: ordersDishDao) : IOrdersDish {
    override suspend fun insertOrderDish(ordersDish: OrdersDish) {
        ordersDishDao.insertOrderDish(ordersDish)
    }

    override suspend fun deleteOrderDish(ordersDish: OrdersDish) {
        ordersDishDao.deleteOrderDish(ordersDish)
    }
}