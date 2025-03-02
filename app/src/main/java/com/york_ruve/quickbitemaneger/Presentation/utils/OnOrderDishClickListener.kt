package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity

interface OnOrderDishClickListener {
    fun onDeleteIconClick(dish: dishWithQuantity, orderId:Int)
    fun onQuantityChanged(dish: dishWithQuantity, orderId: Int, newQuantity: Int)
}