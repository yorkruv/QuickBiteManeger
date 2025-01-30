package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes

@Dao
interface ordersDao {

    @Query("SELECT * FROM Orders")
    fun getAllOrders(): List<ordersEntity>

    @Transaction
    @Query("SELECT * FROM Orders")
    fun getAllOrdersWithDishes(): List<orderWithDishes>

    @Query("SELECT * FROM Orders WHERE orderId = :id")
    fun getOrderById(id: Int): ordersEntity

    @Query("SELECT * FROM Orders WHERE estado = :state")
    fun getOrdersByState(state: String): List<ordersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: ordersEntity)

    @Update
    fun updateOrder(order: ordersEntity)

    @Delete
    fun deleteOrder(order: ordersEntity)

}