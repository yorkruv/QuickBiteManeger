package com.york_ruve.quickbitemaneger.Domain.UsesCases.sales

import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class deleteSalesUseCase @Inject constructor(private val saleRepository: ISaleRepository) {
    suspend operator fun invoke(sales: Sales) {
        saleRepository.deleteSales(sales)
    }
}