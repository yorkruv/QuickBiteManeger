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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.clientsViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.salesViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.DateAxisFormatter
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint
import com.york_ruve.quickbitemaneger.Presentation.utils.LocateHelper
import com.york_ruve.quickbitemaneger.Presentation.utils.NotificationHelper
import kotlinx.coroutines.launch


@AndroidEntryPoint
class dashBoardFragment : Fragment() {
    private val salesViewModel: salesViewModel by viewModels()
    private val ordersViewModel: ordersViewModel by viewModels()
    private val dishViewModel: dishViewModel by viewModels()
    private val clientsViewModel: clientsViewModel by viewModels()
    private val ingredientViewModel: ingredientViewModel by viewModels()
    private lateinit var binding: FragmentDashBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setUpSpinner()
        viewObservers()
        setListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            val listCritical = ingredientViewModel.getCriticalIngredients()
            Log.d("criticalIngredients", "$listCritical")
        }
        viewLifecycleOwner.lifecycleScope.launch {
            val criticalList = ingredientViewModel.getCriticalIngredients()
            if (criticalList.isNotEmpty()) {
                NotificationHelper.createNotificationChannel(requireContext())
                NotificationHelper.showCriticalIngredientNotification(requireContext(), criticalList)
            }
        }

    }

    private fun init() {
        val locateHelper = LocateHelper()
        val newContext = locateHelper.setLocale(requireContext(), locateHelper.getSavedLanguage(requireContext()))
        ordersViewModel.getOrdersByState(newContext.getString(R.string.pending))
        ingredientViewModel.getAllIngredients()
    }

    private fun setListeners() {
        binding.spRangoVentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                salesViewModel.getSalesDates(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        binding.btnOrders.setOnClickListener {
            val fragmentOrders = OrdersFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.MainFragment, fragmentOrders)
                .addToBackStack(null)
                .commit()
            (requireActivity() as MainActivity).updateToolbarTitleAndNavigation(requireContext().getString(R.string.Order_management), R.id.nav_orders)
        }
        binding.btnInventory.setOnClickListener {
            val fragmentInventory = InventoryFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.MainFragment, fragmentInventory)
                .addToBackStack(null)
                .commit()
            (requireActivity() as MainActivity).updateToolbarTitleAndNavigation(requireContext().getString(R.string.Inventory_management), R.id.nav_inventory)
        }
        binding.btnSales.setOnClickListener {
            val fragmentSales = SalesFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.MainFragment, fragmentSales)
                .addToBackStack(null)
                .commit()
            (requireActivity() as MainActivity).updateToolbarTitleAndNavigation(requireContext().getString(R.string.Sales_management), R.id.nav_inventory)
        }
        binding.btnClients.setOnClickListener {
            val fragmentClients = ClientsFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.MainFragment, fragmentClients)
                .addToBackStack(null)
                .commit()
            (requireActivity() as MainActivity).updateToolbarTitleAndNavigation(requireContext().getString(R.string.Clients_management), 0)
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
        ordersViewModel.ordersPending.observe(viewLifecycleOwner) {
            binding.tvNumberPendingOrders.text = "$it"
        }
        ingredientViewModel.ingredientsCriticals.observe(viewLifecycleOwner) {
            binding.tvNumberInvCritical.text = "$it"
        }
        dishViewModel.IngredientsByDishId.observe(viewLifecycleOwner) { dishWithIngredients ->
            if (dishWithIngredients != null) {
                android.util.Log.d("dishIngredients", "$dishWithIngredients")
                val ingredientList = dishWithIngredients.ingredients.map { it.ingredientId }
                ingredientViewModel.getDishIngredientsById(
                    dishWithIngredients.dish.dishId,
                    ingredientList
                )
            }
        }
        ingredientViewModel.dishIngredients.observe(viewLifecycleOwner) { dishIngredientsList ->
            dishIngredientsList.forEach { dishIngredient ->
                Log.d("dishWithQuantity", "$dishIngredient")
                val quantity = dishIngredient.quantity
                Log.d("Ingrediente ${dishIngredient.ingredientId}", "Cantidad: $quantity")

                ingredientViewModel.SubstractIngredientStock(dishIngredient.ingredientId, quantity)
            }
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

    private fun isNightMode(): Boolean {
        return when (requireContext().resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }
    }


}
