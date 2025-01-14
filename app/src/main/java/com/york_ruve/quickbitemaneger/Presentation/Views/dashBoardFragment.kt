package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.components.Legend
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

    }

    private fun prueba() {
        val time = System.currentTimeMillis()
        val venta = Sales(1, 1, 1, 1736750119000, 5787.0)
        val venta2 = Sales(2, 2, 1, 1673726514000, 1575764.0)
        val venta3 = Sales(3, 3, 1, fecha = 1705262514000, 10000.0)
        salesViewModel.addSale(venta)
        salesViewModel.addSale(venta2)
        salesViewModel.addSale(venta3)

    }

    private fun setListeners() {
        binding.spRangoVentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                GlobalScope
                salesViewModel.getSalesDates(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }


    private fun viewObservers() {
        salesViewModel.lineChartData.observe(viewLifecycleOwner) {
            val dataSet = LineDataSet(it, "").apply {
                if (isNightMode()) {
                    color = Color.WHITE
                    valueTextColor = Color.WHITE
                    lineWidth = 2f
                    circleRadius = 4f
                    setCircleColor(Color.WHITE)
                } else {
                    color = Color.BLACK
                    valueTextColor = Color.BLACK
                    lineWidth = 2f
                    circleRadius = 4f
                    setCircleColor(Color.BLACK)
                }
                form = Legend.LegendForm.NONE
            }
            val lineData = LineData(dataSet)
            binding.lineChart.apply {
                data = lineData
                setDrawGridBackground(false)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.valueFormatter = DateAxisFormatter(
                    salesViewModel.salesDates.value ?: emptyList()
                ) // Etiquetas de fechas
                xAxis.textColor = if (isNightMode()) Color.WHITE else Color.BLACK
                axisLeft.textColor = if (isNightMode()) Color.WHITE else Color.BLACK
                axisRight.isEnabled = false
                invalidate() // Refresca el gráfico
            }
        }
        salesViewModel.totalSalesData.observe(viewLifecycleOwner) {
            val orderstx = getString(R.string.nav_orders)
            binding.tvTotalSales.text = "$${it.totalSalesAmount} "
            binding.tvTotalOrders.text = "${it.totalSalesCount} ${orderstx}"
        }
        salesViewModel.salesToDay.observe(viewLifecycleOwner) {
            binding.tvNumberSalesDay.text = "$it"
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
