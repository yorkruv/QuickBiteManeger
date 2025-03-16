package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Domain.Model.Dish

interface OnDishClickListener {
    fun onEditDishClick(dish: DishWithIngredients)
    fun onDeleteDishClick(dish: DishWithIngredients)
}