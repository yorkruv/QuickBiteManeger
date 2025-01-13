package com.york_ruve.quickbitemaneger.Domain.UsesCases.sales

import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class insertSalesUseCase @Inject constructor(private val saleRepository: ISaleRepository) {
    suspend operator fun invoke(sales: Sales) {
        saleRepository.insertSales(sales)
    }
}