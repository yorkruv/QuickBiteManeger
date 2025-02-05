package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository
import javax.inject.Inject


class getClientByIdUseCase @Inject constructor(private val clientsRepository: IClientsRepository) {
    suspend operator fun invoke(id: Int): Clients? {
        return clientsRepository.getClientById(id)
    }

}