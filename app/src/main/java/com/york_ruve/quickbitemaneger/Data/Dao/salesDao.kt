package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.SalesEntity
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByDay
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByMonth
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByYear
import com.york_ruve.quickbitemaneger.Data.Filter.TotalSalesData

@Dao
interface salesDao {

    @Query("SELECT * FROM sales")
    fun getAllSales(): List<SalesEntity>

    @Query("""
    SELECT 
        COUNT(*) AS totalSalesCount,
        SUM(total) AS totalSalesAmount
    FROM sales
""")
    fun getTotalSalesAndAmount(): TotalSalesData

    @Query("SELECT * FROM sales WHERE id = :id")
    fun getSalesById(id: Int): SalesEntity?

    @Query("""
        SELECT 
            strftime('%Y-%m-%d', datetime(fecha / 1000, 'unixepoch')) AS saleDate,
            SUM(total) AS totalSales,
            COUNT(*) AS totalTransactions
        FROM sales
        GROUP BY saleDate
        ORDER BY saleDate ASC
    """)
    fun getSalesByDay(): List<SalesByDay>

    @Query("""
        SELECT 
            strftime('%Y-%m', datetime(fecha / 1000, 'unixepoch')) AS saleMonth,
            SUM(total) AS totalSales,
            COUNT(*) AS totalTransactions
        FROM sales
        GROUP BY saleMonth
        ORDER BY saleMonth ASC
    """)
    fun getSalesGroupedByMonth(): List<SalesByMonth>

    @Query("""
        SELECT 
            strftime('%Y', datetime(fecha / 1000, 'unixepoch')) AS saleYear,
            SUM(total) AS totalSales,
            COUNT(*) AS totalTransactions
        FROM sales
        GROUP BY saleYear
        ORDER BY saleYear ASC
    """)
    fun getSalesGroupedByYear(): List<SalesByYear>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSales(sales: SalesEntity)

    @Update
    fun updateSales(sales: SalesEntity)

    @Delete
    fun deleteSales(sales: SalesEntity)

}