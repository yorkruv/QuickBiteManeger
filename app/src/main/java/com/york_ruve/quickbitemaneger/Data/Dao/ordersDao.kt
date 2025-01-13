package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity

@Dao
interface ordersDao {
    @Query("SELECT * FROM Orders")
    fun getAllOrders(): List<ordersEntity>

    @Query("SELECT * FROM Orders WHERE id = :id")
    fun getOrderById(id: Int): ordersEntity?

    @Query("SELECT * FROM Orders WHERE estado = :state")
    fun getOrdersByState(state: String): List<ordersEntity>

    @Insert
    fun insertOrder(order: ordersEntity)

    @Update
    fun updateOrder(order: ordersEntity)

    @Delete
    fun deleteOrder(order: ordersEntity)

}