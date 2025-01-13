package com.york_ruve.quickbitemaneger.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String
    )