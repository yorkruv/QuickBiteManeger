package com.york_ruve.quickbitemaneger.Data.Entities


import androidx.room.Entity
import androidx.room.Index


@Entity(
    tableName = "OrdersDish",
    primaryKeys = ["orderId", "dishId"],
    indices = [Index(value = ["orderId", "dishId"])]
)
data class OrdersDish(
    val orderId: Int,
    val dishId: Int,
    val quantity: Int
)