package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository

class getAllClientsUseCase(private val clientsRepository: IClientsRepository) {
    suspend operator fun invoke():List<Clients> {
        return clientsRepository.getAllClients()
    }
}