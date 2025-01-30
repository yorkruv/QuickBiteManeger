package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dishes")
data class dishEntity(
    @PrimaryKey(autoGenerate = true) val dishId: Int? = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double
)