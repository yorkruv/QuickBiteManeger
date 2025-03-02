package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Data.Relations.ClientWithOrdersAndDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.deleteClientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.getAllClientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.getAllClientsWithOrdersAndDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.getClientByIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.getClientByNameUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.insertClientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.updateClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class clientsViewModel @Inject constructor(
    private val getAllclientsUseCase: getAllClientsUseCase,
    private val insertClientUseCase: insertClientUseCase,
    private val updateClientUseCase: updateClientUseCase,
    private val deleteClientUseCase: deleteClientUseCase,
    private val getClientByIdUseCase: getClientByIdUseCase,
    private val getAllClientsWithOrdersAndDishesUseCase: getAllClientsWithOrdersAndDishesUseCase,
    private val getClientByNameUseCase: getClientByNameUseCase
) : ViewModel() {
    private val _clients = MutableLiveData<List<Clients>>()
    val clients: LiveData<List<Clients>> = _clients

    private val _client = MutableLiveData<Clients?>()
    val client: LiveData<Clients?> = _client

    private val _clientsWithOrdersAndDishes = MutableLiveData<List<ClientWithOrdersAndDishes>>()
    val clientsWithOrdersAndDishes: LiveData<List<ClientWithOrdersAndDishes>> = _clientsWithOrdersAndDishes

    init {
        loadClients()
    }

    fun loadClients() {
        viewModelScope.launch {
            _clients.value = getAllclientsUseCase()
        }
    }

    fun loadClientById(id: Int) {
        viewModelScope.launch {
            _client.value = getClientByIdUseCase(id)
        }
    }

    fun loadClientByName(name: String) {
        viewModelScope.launch {
            _client.value = getClientByNameUseCase(name)
        }
    }

    fun loadClientsWithOrdersAndDishes() {
        viewModelScope.launch {
            _clientsWithOrdersAndDishes.value = getAllClientsWithOrdersAndDishesUseCase()
        }
    }

    fun addClient(client: Clients) {
        viewModelScope.launch {
            insertClientUseCase(client)
            loadClients()
        }
    }

    fun updateClient(client: Clients) {
        viewModelScope.launch {
            updateClientUseCase(client)
            loadClients()
        }
    }

    fun deleteClient(client: Clients) {
        viewModelScope.launch {
            deleteClientUseCase(client)
            loadClients()
        }
    }

}