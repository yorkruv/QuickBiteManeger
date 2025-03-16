package com.york_ruve.quickbitemaneger.Presentation.utils


interface OnDishIngredientsClickListener {
    fun OnQuantityChanged(dishId: Int, newQuantity: Double, ingredientId: Int)
    fun OnDeleteDishIngredientClick(dishId: Int, ingredientId: Int, quantity:Double)
}