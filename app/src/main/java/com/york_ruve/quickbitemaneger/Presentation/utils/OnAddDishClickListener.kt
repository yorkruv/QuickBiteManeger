package com.york_ruve.quickbitemaneger.Presentation.utils

import android.app.Dialog
import com.york_ruve.quickbitemaneger.Domain.Model.Dish

interface OnAddDishClickListener {
    fun onAddDishClick(dish: Dish, orderId: Int)
}