package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes

@Dao
interface ordersDao {

    @Query("SELECT * FROM Orders")
    fun getAllOrders(): List<ordersEntity>

    @Transaction
    @Query("SELECT * FROM Orders ORDER BY fecha DESC")
    fun getAllOrdersWithDishes(): List<orderWithDishes>

    @Transaction
    @Query("""
    SELECT d.*, od.quantity 
    FROM Dishes d
    INNER JOIN OrdersDish od ON d.dishId = od.dishId
    WHERE od.orderId = :orderId
""")
    fun getAlldishesWithQuantity(orderId: Int): List<dishWithQuantity>

    @Query("SELECT * FROM Orders WHERE orderId = :id")
    fun getOrderById(id: Int): ordersEntity

    @Transaction
    @Query("SELECT * FROM Orders WHERE orderId = :id")
    fun getOrdersWithDishesById(id: Int): orderWithDishes

    @Transaction
    @Query("SELECT * FROM Orders WHERE estado = :state")
    fun getOrdersByState(state: String): List<orderWithDishes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: ordersEntity)

    @Update
    fun updateOrder(order: ordersEntity)

    @Delete
    fun deleteOrder(order: ordersEntity)

}