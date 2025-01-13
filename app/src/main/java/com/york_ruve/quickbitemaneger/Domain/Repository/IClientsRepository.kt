package com.york_ruve.quickbitemaneger.Domain.Repository

import com.york_ruve.quickbitemaneger.Domain.Model.Clients

interface IClientsRepository {
    suspend fun getAllClients(): List<Clients>
    suspend fun insertClient(client: Clients)
    suspend fun updateClient(client: Clients)
    suspend fun deleteClient(client: Clients)
}