package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.AndroidViewModel
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.deleteOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersWithDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAlldishesWithQuantityUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getOrderByIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getOrdersByStateUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getOrdersWithDishesById
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.insertOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.updateOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.deleteOrderDishUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.insertOrderDishUseCase
import com.york_ruve.quickbitemaneger.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ordersViewModel @Inject constructor(
    application:Application,
    private val getAllOrdersUseCase: getAllOrdersUseCase,
    private val insertOrderUseCase: insertOrdersUseCase,
    private val updateOrderUseCase: updateOrdersUseCase,
    private val deleteOrderUseCase: deleteOrdersUseCase,
    private val getOrdersByStateUseCase: getOrdersByStateUseCase,
    private val insertOrderDishUseCase: insertOrderDishUseCase,
    private val deleteOrderDishUseCase: deleteOrderDishUseCase,
    private val getAllOrdersWithDishesUseCase: getAllOrdersWithDishesUseCase,
    private val getOrdersByIdUseCase: getOrderByIdUseCase,
    private val getOrdersWithDishesById: getOrdersWithDishesById,
    private val getAllDishesWithQuantityUseCase: getAlldishesWithQuantityUseCase
) : AndroidViewModel(application) {
    private val _orders = MutableLiveData<List<Orders>>()
    val orders: LiveData<List<Orders>> = _orders

    private val _order = MutableLiveData<Orders>()
    val order: LiveData<Orders> = _order

    private val _orderDish = MutableLiveData<List<orderWithDishes>>()
    val orderDish: LiveData<List<orderWithDishes>> = _orderDish

    private val _orderDishById = MutableLiveData<orderWithDishes>()
    val orderDishById:LiveData<orderWithDishes> = _orderDishById

    private val _ordersPending = MutableLiveData<Int>()
    val ordersPending: LiveData<Int> = _ordersPending

    private val _dishWithQuantity = MutableLiveData<List<dishWithQuantity>>()
    val dishWithQuantity: LiveData<List<dishWithQuantity>> = _dishWithQuantity

    private val localizedContext: Context

    init {
        // Obtener el idioma guardado en preferencias
        val sharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val languageCode = sharedPreferences.getString("language", "en") ?: "en"

        // Crear contexto con el idioma configurado
        val locale = Locale(languageCode)
        val config = Configuration(application.resources.configuration)
        config.setLocale(locale)
        localizedContext = application.createConfigurationContext(config)
    }


    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val orders = getAllOrdersUseCase()
            _orders.postValue(orders)
        }

    }

    fun loadOrderById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val order = getOrdersByIdUseCase(id)
            _order.postValue(order)
        }
    }

    fun loadOrderDishById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val orderDish = getOrdersWithDishesById(id)
            _orderDishById.postValue(orderDish)
        }
    }

    fun loadDishWithQuantity(orderId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val dishWithQuantity = getAllDishesWithQuantityUseCase(orderId)
            _dishWithQuantity.postValue(dishWithQuantity)
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
            _orderDish.postValue(orders)
            if (state == localizedContext.getString(R.string.pending)) {

                var aux = orders.size
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