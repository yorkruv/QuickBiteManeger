package com.york_ruve.quickbitemaneger.Domain.UsesCases.sales

import androidx.lifecycle.LiveData
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByDay
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class getSalesByDayUseCase @Inject constructor(private val saleRepository: ISaleRepository) {
    operator fun invoke(): List<SalesByDay> {
        return saleRepository.getSalesByDay()
    }

}