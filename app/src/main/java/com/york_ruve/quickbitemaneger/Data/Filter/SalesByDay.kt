package com.york_ruve.quickbitemaneger.Data.Filter

data class SalesByDay (
    val saleDate: String, // Formato: YYYY-MM-DD
    val totalSales: Double,
    val totalTransactions: Int
)
