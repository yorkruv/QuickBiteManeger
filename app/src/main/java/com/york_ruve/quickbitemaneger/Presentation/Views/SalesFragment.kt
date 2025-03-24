package com.york_ruve.quickbitemaneger.Presentation.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.salesViewModel
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentSalesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalesFragment : Fragment() {

    private lateinit var binding: FragmentSalesBinding

    private val salesViewModel: salesViewModel by viewModels()
    private val orderViewModel: ordersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewObserver()
    }

    private fun init() {
        salesViewModel.loadSales()
        orderViewModel.getOrdersByState(getString(R.string.pending))
    }

    private fun viewObserver() {
        salesViewModel.amountToDay.observe(viewLifecycleOwner) {
            binding.tvTotalSoldToday.text = it.toString()
        }
        salesViewModel.salesToDay.observe(viewLifecycleOwner) {
            binding.tvSalesDay.text = it.toString()
        }
        salesViewModel.averegeTicket.observe(viewLifecycleOwner) {
            binding.tvAverageTicket.text = String.format("%.2f", it)
        }

        orderViewModel.ordersPending.observe(viewLifecycleOwner) {
            binding.tvPendingSales.text = it.toString()
        }

    }

}