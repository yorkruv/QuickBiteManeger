package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Orders")
data class ordersEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Int? = 0,
    val fecha: String = "",
    val id_cliente: Int = 0,
    val estado: String = "",
    val total: Double = 0.0
    )