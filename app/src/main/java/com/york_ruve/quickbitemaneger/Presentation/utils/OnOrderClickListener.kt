package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes

interface OnOrderClickListener {
    fun onOrderClick(orden: orderWithDishes)
    fun onStateClick(orden: orderWithDishes)
    fun showStateDialog(orden: orderWithDishes)
    fun showOrderDialog(orden: orderWithDishes)
}