package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SalesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val id_pedido: Int = 0,
    val id_cliente: Int = 0,
    val fecha: Long,
    val total: Double = 0.0,
    val metodo_pago: String = ""
)