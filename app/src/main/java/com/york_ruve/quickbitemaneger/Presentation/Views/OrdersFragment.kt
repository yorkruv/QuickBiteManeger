package com.york_ruve.quickbitemaneger.Presentation.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.clientsViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OrdersFragment : Fragment(), OnOrderClickListener {
    private lateinit var binding: FragmentOrdersBinding

    private val ordersViewModel: ordersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setlistener()
        deselectnav2()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        ordersViewModel.getOrdersDish()
        ordersViewModel.orderDish.observe(viewLifecycleOwner){
            binding.rvOrders.layoutManager = LinearLayoutManager(this.context)
            binding.rvOrders.adapter = ordersAdapter(it,this)
        }

    }

    private fun setlistener() {
        binding.bottomNavigationOrderState.setOnItemSelectedListener { item ->

            binding.bottomNavigationOrderState2.menu.setGroupCheckable(0, true, false)
            for (i in 0 until binding.bottomNavigationOrderState2.menu.size()) {
                binding.bottomNavigationOrderState2.menu.getItem(i).isChecked = false
            }
            binding.bottomNavigationOrderState2.menu.setGroupCheckable(0, true, true)
            when (item.itemId) {
                R.id.all -> {}
                R.id.Pending -> {}
                R.id.in_preparation -> {}
            }
            true
        }
        binding.bottomNavigationOrderState2.setOnItemSelectedListener { item ->
            binding.bottomNavigationOrderState.menu.setGroupCheckable(0, true, false)
            for (i in 0 until binding.bottomNavigationOrderState.menu.size()) {
                binding.bottomNavigationOrderState.menu.getItem(i).isChecked = false
            }
            binding.bottomNavigationOrderState.menu.setGroupCheckable(0, true, true)
            when (item.itemId) {
                R.id.ready -> {}
                R.id.Delivered -> {}
                R.id.canceled -> {}
            }
            true
        }
    }

    private fun deselectnav2() {
        binding.bottomNavigationOrderState2.menu.setGroupCheckable(0, true, false)
        for (i in 0 until binding.bottomNavigationOrderState2.menu.size()) {
            binding.bottomNavigationOrderState2.menu.getItem(i).isChecked = false
        }
        binding.bottomNavigationOrderState2.menu.setGroupCheckable(0, true, true)
    }

    override fun onOrderClick(orden: orderWithDishes) {
        Toast.makeText(requireContext(), "Orden seleccionada: ${orden.order.orderId}", Toast.LENGTH_SHORT).show()
    }

    override fun onStateClick(orden: orderWithDishes) {
        Toast.makeText(requireContext(), "Estado seleccionado: ${orden.order.estado}", Toast.LENGTH_SHORT).show()
    }
}