package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Orders")
data class ordersEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Int? = 0,
    val fecha: String = "",
    val cliente: String = "",
    val estado: String = "",
    val total: Double = 0.0
    )