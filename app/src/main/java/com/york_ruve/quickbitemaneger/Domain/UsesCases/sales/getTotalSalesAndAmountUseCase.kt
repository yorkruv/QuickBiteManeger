package com.york_ruve.quickbitemaneger.Domain.UsesCases.sales

import com.york_ruve.quickbitemaneger.Data.Filter.TotalSalesData
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class getTotalSalesAndAmountUseCase @Inject constructor(private val saleRepository: ISaleRepository) {
    suspend operator fun invoke(): TotalSalesData {
        return saleRepository.getTotalSalesAndAmount()
    }

}