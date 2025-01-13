package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.deleteOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.insertOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.updateOrdersUseCase
import kotlinx.coroutines.launch

class ordersViewModel(
    private val getAllOrdersUseCase: getAllOrdersUseCase,
    private val insertOrderUseCase: insertOrdersUseCase,
    private val updateOrderUseCase: updateOrdersUseCase,
    private val deleteOrderUseCase: deleteOrdersUseCase,
) : ViewModel() {
    private val _orders = MutableLiveData<List<Orders>>()
    val orders: LiveData<List<Orders>> = _orders

    fun loadOrders() {
        viewModelScope.launch {
            _orders.value = getAllOrdersUseCase()
        }

    }

    fun addOrder(order: Orders) {
        viewModelScope.launch {
            insertOrderUseCase(order)
            loadOrders()
        }
    }

    fun updateOrder(order: Orders) {
        viewModelScope.launch {
            updateOrderUseCase(order)
            loadOrders()
        }

    }

    fun deleteOrder(order: Orders) {
        viewModelScope.launch {
            deleteOrderUseCase(order)
            loadOrders()
        }

    }

}