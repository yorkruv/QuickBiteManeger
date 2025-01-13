package com.york_ruve.quickbitemaneger.Data.Repository

import com.york_ruve.quickbitemaneger.Data.Dao.clientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity
import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository

class clientsRepository(private val clientsDao: clientsDao): IClientsRepository {
    override suspend fun getAllClients(): List<Clients> {
        return clientsDao.getAllClients().map {
            Clients(it.id,it.nombre,it.direccion,it.telefono, it.email)
        }
    }

    override suspend fun insertClient(client: Clients) {
        val clientsEntity = ClientsEntity(client.id,client.nombre,client.direccion,client.telefono, client.email)
        clientsDao.insertClient(clientsEntity)
    }

    override suspend fun updateClient(client: Clients) {
        val clientsEntity = ClientsEntity(client.id,client.nombre,client.direccion,client.telefono, client.email)
        clientsDao.updateClient(clientsEntity)
    }

    override suspend fun deleteClient(client: Clients) {
        val clientsEntity = ClientsEntity(client.id,client.nombre,client.direccion,client.telefono, client.email)
        clientsDao.deleteClient(clientsEntity)
    }
}