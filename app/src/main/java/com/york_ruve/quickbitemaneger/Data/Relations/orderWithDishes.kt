package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity


data class orderWithDishes (
    @Embedded val order: ordersEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "dishId",
        associateBy = Junction(OrdersDish::class)
    )
    val dishes: List<dishEntity>

)