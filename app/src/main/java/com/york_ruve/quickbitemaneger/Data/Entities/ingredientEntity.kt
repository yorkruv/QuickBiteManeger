package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredients")
data class ingredientEntity(
    @PrimaryKey(autoGenerate = true) val ingredientId:Int? = 0,
    val nombre: String,
    val stock: Double,
    val unidad: String,
    val criticalQuantity: Double
)