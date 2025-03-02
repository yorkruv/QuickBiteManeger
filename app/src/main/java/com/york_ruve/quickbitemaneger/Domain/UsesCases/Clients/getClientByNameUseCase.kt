package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository
import javax.inject.Inject

class getClientByNameUseCase @Inject constructor(private val clientsRepository: IClientsRepository) {
    suspend operator fun invoke(name: String): Clients? {
        return clientsRepository.getClientByName(name)
    }

}