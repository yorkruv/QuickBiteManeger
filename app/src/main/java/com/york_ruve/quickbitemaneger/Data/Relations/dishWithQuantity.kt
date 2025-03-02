package com.york_ruve.quickbitemaneger.Data.Relations

import androidx.room.Embedded
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity


data class dishWithQuantity(
    @Embedded val dish: dishEntity,
    val quantity: Int
)
