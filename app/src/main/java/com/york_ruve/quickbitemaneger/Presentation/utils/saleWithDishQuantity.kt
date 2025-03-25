package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Model.Sales

data class saleWithDishQuantity (
    val sale:Sales,
    val dishQuantity:List<dishWithQuantity>
)