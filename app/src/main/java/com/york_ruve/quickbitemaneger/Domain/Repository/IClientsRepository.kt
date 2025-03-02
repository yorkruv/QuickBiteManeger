package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Data.Relations.ClientWithOrdersAndDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Clients

interface IClientsRepository {
    suspend fun getAllClients(): List<Clients>
    suspend fun getClientById(id: Int): Clients?
    suspend fun getAllClientsWithOrdersAndDishes(): List<ClientWithOrdersAndDishes>
    suspend fun getClientByName(name: String): Clients?
    suspend fun insertClient(client: Clients)
    suspend fun updateClient(client: Clients)
    suspend fun deleteClient(client: Clients)
}