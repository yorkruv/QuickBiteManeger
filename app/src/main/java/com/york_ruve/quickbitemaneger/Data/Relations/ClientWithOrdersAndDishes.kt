package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity

data class ClientWithOrdersAndDishes(
    @Embedded val client:ClientsEntity,
    @Relation(
        entity = ordersEntity::class,
        parentColumn = "nombre",
        entityColumn = "cliente"
    )
    val orders: List<orderWithDishes>
    )
