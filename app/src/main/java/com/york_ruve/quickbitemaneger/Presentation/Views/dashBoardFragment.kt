package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.salesViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.DateAxisFormatter
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class dashBoardFragment : Fragment() {
    private val salesViewModel: salesViewModel by viewModels()
    private lateinit var binding: FragmentDashBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
        viewObservers()
        setListeners()
        prueba()
        Log.d("Fechas", salesViewModel.salesDates.value.toString())
    }

    private fun prueba() {
        val time = System.currentTimeMillis() / 1000
        val venta = Sales(1, 1, 1, 1705077600000, 1.0)
        val venta2 = Sales(2, 2, 1, 6786, 1575764.0)
        val venta3 = Sales(3, 3, 1, 8765, 10000.0)
        salesViewModel.addSale(venta)
        salesViewModel.addSale(venta2)
        salesViewModel.addSale(venta3)

    }

    private fun setListeners() {

    }


    private fun viewObservers() {
        salesViewModel.lineChartData.observe(viewLifecycleOwner) {
            val dataSet = LineDataSet(it, "Ventas por día").apply {
                if (isNightMode()) {
                    color = R.color.white
                    valueTextColor = R.color.white
                    lineWidth = 2f
                    circleRadius = 4f
                    setCircleColor(R.color.white)
                }else{
                    color = R.color.black
                    valueTextColor = R.color.black
                    lineWidth = 2f
                    circleRadius = 4f
                    setCircleColor(R.color.black)
                }
            }
            val lineData = LineData(dataSet)
            binding.lineChart.apply {
                data = lineData
                description.text = "Ventas diarias"
                setDrawGridBackground(false)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.valueFormatter = DateAxisFormatter(
                    salesViewModel.salesDates.value ?: emptyList()
                ) // Etiquetas de fechas
                axisRight.isEnabled = false
                invalidate() // Refresca el gráfico
            }
        }
        salesViewModel.totalSalesData.observe(viewLifecycleOwner) {
            val orderstx = getString(R.string.nav_orders)
            binding.tvTotalSales.text = "$${it.totalSalesAmount} "
            binding.tvTotalOrders.text = "${it.totalSalesCount} ${orderstx}"
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.rango_de_ventas,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spRangoVentas.adapter = adapter
        }
    }

    fun isNightMode(): Boolean {
        return when (requireContext().resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }
    }

}
