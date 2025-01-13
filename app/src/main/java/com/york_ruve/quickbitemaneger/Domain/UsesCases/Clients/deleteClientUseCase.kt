package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository
import kotlinx.coroutines.GlobalScope

class deleteClientUseCase(private val clientsRepository: IClientsRepository) {
    suspend operator fun invoke(clients: Clients) {
        clientsRepository.deleteClient(clients)

    }
}