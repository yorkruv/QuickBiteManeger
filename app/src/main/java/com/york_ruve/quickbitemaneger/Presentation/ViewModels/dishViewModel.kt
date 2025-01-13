package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getAllDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.insertDishWithIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.GetAllSalesUseCase
import kotlinx.coroutines.launch

class dishViewModel(
    private val getAllDishesUseCase: getAllDishesUseCase,
    private val insertDishWithIngredientsUseCase: insertDishWithIngredientsUseCase
):ViewModel() {
    private val _dish = MutableLiveData<List<Dish>>()
    val dish:LiveData<List<Dish>> = _dish

    fun loadDishes(){
        viewModelScope.launch {
            _dish.value = getAllDishesUseCase()
        }
    }

    fun inserDish(dish: Dish,ingredients:List<Pair<Int,Double>>){
        viewModelScope.launch {
            insertDishWithIngredientsUseCase(dish,ingredients)
        }
    }
}