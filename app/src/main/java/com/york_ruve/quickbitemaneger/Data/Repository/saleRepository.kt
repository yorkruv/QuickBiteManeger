package com.york_ruve.quickbitemaneger.Data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.Entities.SalesEntity
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByDay
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByMonth
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByYear
import com.york_ruve.quickbitemaneger.Data.Filter.TotalSalesData
import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import javax.inject.Inject

class saleRepository @Inject constructor(private val salesDao: salesDao) : ISaleRepository {

    override suspend fun getAllSales(): List<Sales> {
        return salesDao.getAllSales().map {
            Sales(it.id, it.id_pedido, it.cliente, it.fecha, it.total)
        }
    }

    override suspend fun getTotalSalesAndAmount(): TotalSalesData {
        return salesDao.getTotalSalesAndAmount()
    }


    override suspend fun insertSales(sales: Sales) {
        val salesEntity =
            SalesEntity(sales.id, sales.id_pedido, sales.cliente, sales.fecha, sales.total)
        salesDao.insertSales(salesEntity)
    }

    override suspend fun updateSales(sales: Sales) {
        val salesEntity =
            SalesEntity(sales.id, sales.id_pedido, sales.cliente, sales.fecha, sales.total)
        salesDao.updateSales(salesEntity)
    }

    override suspend fun deleteSales(sales: Sales) {
        val salesEntity =
            SalesEntity(sales.id, sales.id_pedido, sales.cliente, sales.fecha, sales.total)
        salesDao.deleteSales(salesEntity)

    }

    override fun getSalesByDay(): List<SalesByDay> {
        return salesDao.getSalesByDay()
    }

    override suspend fun getSalesByMonth(): List<SalesByMonth> {
        return salesDao.getSalesGroupedByMonth()
    }

    override suspend fun getSalesByYear(): List<SalesByYear> {
        return salesDao.getSalesGroupedByYear()
    }

}