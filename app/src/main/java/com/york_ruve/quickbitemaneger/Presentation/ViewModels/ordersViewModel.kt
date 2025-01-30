package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.deleteOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersWithDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getOrdersByStateUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.insertOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.updateOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.deleteOrderDishUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.insertOrderDishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ordersViewModel @Inject constructor(
    private val getAllOrdersUseCase: getAllOrdersUseCase,
    private val insertOrderUseCase: insertOrdersUseCase,
    private val updateOrderUseCase: updateOrdersUseCase,
    private val deleteOrderUseCase: deleteOrdersUseCase,
    private val getOrdersByStateUseCase: getOrdersByStateUseCase,
    private val insertOrderDishUseCase: insertOrderDishUseCase,
    private val deleteOrderDishUseCase: deleteOrderDishUseCase,
    private val getAllOrdersWithDishesUseCase: getAllOrdersWithDishesUseCase
) : ViewModel() {
    private val _orders = MutableLiveData<List<Orders>>()
    val orders: LiveData<List<Orders>> = _orders

    private val _orderDish = MutableLiveData<List<orderWithDishes>>()
    val orderDish: LiveData<List<orderWithDishes>> = _orderDish

    private val _ordersPending = MutableLiveData<Int>()
    val ordersPending: LiveData<Int> = _ordersPending


    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val orders = getAllOrdersUseCase()
            _orders.postValue(orders)
        }

    }

    fun addOrder(order: Orders) {
        viewModelScope.launch(Dispatchers.IO) {
            insertOrderUseCase(order)
            loadOrders()
        }
    }

    fun addOrderDish(orderDish: OrdersDish) {
        viewModelScope.launch(Dispatchers.IO) {
            insertOrderDishUseCase(orderDish)
            getOrdersDish()
        }
    }

    fun updateOrder(order: Orders) {
        viewModelScope.launch(Dispatchers.IO) {
            updateOrderUseCase(order)
            loadOrders()
        }

    }

    fun deleteOrder(order: Orders) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteOrderUseCase(order)
            loadOrders()
        }

    }

    fun deleteOrderDish(orderDish: OrdersDish) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteOrderDishUseCase(orderDish)
        }
    }

    fun getOrdersByState(state: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val orders = getOrdersByStateUseCase(state)
            _orders.postValue(orders)
            if (state == "Pendiente") {
                var aux:Int = 0
                for (order in orders) {
                    aux += 1
                }
                _ordersPending.postValue(aux)
            }
        }
    }

    fun getOrdersDish(){
        viewModelScope.launch(Dispatchers.IO) {
            val orderDish = getAllOrdersWithDishesUseCase()
            Log.d("TAG", "getOrdersDish: $orderDish")
            _orderDish.postValue(orderDish)
        }
    }
}