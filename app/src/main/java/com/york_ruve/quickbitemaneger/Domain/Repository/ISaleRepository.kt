package com.york_ruve.quickbitemaneger.Domain.Repository

import androidx.lifecycle.LiveData
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByDay
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByMonth
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByYear
import com.york_ruve.quickbitemaneger.Data.Filter.TotalSalesData
import com.york_ruve.quickbitemaneger.Domain.Model.Sales

interface ISaleRepository {
    suspend fun getAllSales(): List<Sales>
    suspend fun getTotalSalesAndAmount(): TotalSalesData
    suspend fun insertSales(sales: Sales)
    suspend fun updateSales(sales: Sales)
    suspend fun deleteSales(sales: Sales)
    fun getSalesByDay():List<SalesByDay>
    suspend fun getSalesByMonth(): List<SalesByMonth>
    suspend fun getSalesByYear(): List<SalesByYear>
}