package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.york_ruve.quickbitemaneger.Data.Filter.SalesByDay
import com.york_ruve.quickbitemaneger.Data.Filter.TotalSalesData
import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.GetAllSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.deleteSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.getSalesByDayUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.getTotalSalesAndAmountUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.insertSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.updateSalesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class salesViewModel @Inject constructor(
    private val getAllSalesUseCase: GetAllSalesUseCase,
    private val insertSaleUseCase: insertSalesUseCase,
    private val updateSaleUseCase: updateSalesUseCase,
    private val deleteSaleUseCase: deleteSalesUseCase,
    private val getSalesByDayUseCase: getSalesByDayUseCase,
    private val getTotalSalesAndAmountUseCase: getTotalSalesAndAmountUseCase
) : ViewModel() {
    private val _sales = MutableLiveData<List<Sales>>()
    val sales: LiveData<List<Sales>> = _sales

    private val _lineChartData = MutableLiveData<List<Entry>>()
    val lineChartData: LiveData<List<Entry>> = _lineChartData

    private val _totalSalesData = MutableLiveData<TotalSalesData>()
    val totalSalesData: LiveData<TotalSalesData> = _totalSalesData

    private val _salesDates = MutableLiveData<List<String>>()
    val salesDates: LiveData<List<String>> = _salesDates

    init {
        loadSales()
    }

    fun loadSales() {
        viewModelScope.launch(Dispatchers.IO) {
            val sales = getAllSalesUseCase()
            _sales.postValue(sales)
            getSalesByDay()
            getSalesAndAmounts()
            getSalesDates()
        }
    }

    fun addSale(sale: Sales) {
        viewModelScope.launch(Dispatchers.IO) {
            insertSaleUseCase(sale)
            loadSales()
        }
    }

    fun updateSale(sale: Sales) {
        viewModelScope.launch(Dispatchers.IO) {
            updateSaleUseCase(sale)
            loadSales()
        }
    }

    fun deleteSale(sale: Sales) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSaleUseCase(sale)
            loadSales()
        }

    }

    fun getSalesByDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val salesByDay = getSalesByDayUseCase()
            val entries = salesByDay.mapIndexed { index, salesByDay ->
                Entry(index.toFloat(), salesByDay.totalSales.toFloat())
            }
            _lineChartData.postValue(entries)

        }
    }

    fun getSalesDates() {
        viewModelScope.launch(Dispatchers.IO) {
            val salesByDay = getSalesByDayUseCase().map { it.saleDate }
            _salesDates.postValue(salesByDay)
        }
    }

    fun getSalesAndAmounts() {
        viewModelScope.launch(Dispatchers.IO) {
            val totalSalesData = getTotalSalesAndAmountUseCase()
            _totalSalesData.postValue(totalSalesData)
        }
    }

}