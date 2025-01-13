package com.york_ruve.quickbitemaneger.Data.Filter

data class SalesByYear (
    val saleYear: String, // Formato: YYYY
    val totalSales: Double,
    val totalTransactions: Int
)