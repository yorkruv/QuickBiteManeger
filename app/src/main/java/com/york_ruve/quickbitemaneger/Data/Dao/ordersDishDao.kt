package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish

@Dao
interface ordersDishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderDish(ordersDish: OrdersDish)

    @Delete
    fun deleteOrderDish(ordersDish: OrdersDish)

}