package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.deleteClientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.getAllClientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.insertClientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients.updateClientUseCase
import kotlinx.coroutines.launch

class clientsViewModel(
    private val getAllclientsUseCase: getAllClientsUseCase,
    private val insertClientUseCase: insertClientUseCase,
    private val updateClientUseCase: updateClientUseCase,
    private val deleteClientUseCase: deleteClientUseCase
) : ViewModel() {
    private val _clients = MutableLiveData<List<Clients>>()
    val clients: LiveData<List<Clients>> = _clients

    fun loadClients() {
        viewModelScope.launch {
            _clients.value = getAllclientsUseCase()
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