package com.york_ruve.quickbitemaneger.Domain.UsesCases.sales

import com.york_ruve.quickbitemaneger.Data.Filter.SalesByYear
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class getSalesByYearUseCase @Inject constructor(private val saleRepository: ISaleRepository) {
    suspend operator fun invoke(): List<SalesByYear> {
        return saleRepository.getSalesByYear()
    }
}