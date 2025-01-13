package com.york_ruve.quickbitemaneger.Data.Filter

data class SalesByMonth(
    val saleMonth: String, // Formato: YYYY-MM
    val totalSales: Double,
    val totalTransactions: Int
)