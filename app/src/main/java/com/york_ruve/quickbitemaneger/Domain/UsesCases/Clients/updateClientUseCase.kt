package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository

class updateClientUseCase(private val clientsRepository: IClientsRepository) {
    suspend operator fun invoke(client:Clients){
        clientsRepository.updateClient(client)
    }
}