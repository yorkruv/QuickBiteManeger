package com.york_ruve.quickbitemaneger.Domain.UsesCases.Clients

import com.york_ruve.quickbitemaneger.Data.Relations.ClientWithOrdersAndDishes
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository
import javax.inject.Inject

class getAllClientsWithOrdersAndDishesUseCase @Inject constructor(private val clientRepository: IClientsRepository) {
    operator suspend fun invoke():List<ClientWithOrdersAndDishes>{
        return clientRepository.getAllClientsWithOrdersAndDishes()
    }
}