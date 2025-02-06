package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint


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
        showOrderDialog(orden)
    }

    override fun onStateClick(orden: orderWithDishes) {
        showStateDialog(orden)
    }

    override fun showStateDialog(orden: orderWithDishes) {
        val statedialog = Dialog(requireContext())
        statedialog.setContentView(R.layout.dialog_stateorder)

        val cvPending = statedialog.findViewById<CardView>(R.id.cv_pending)
        val cvInPreparation = statedialog.findViewById<CardView>(R.id.cv_in_preparation)
        val cvReady = statedialog.findViewById<CardView>(R.id.cv_ready)
        val cvDelivered = statedialog.findViewById<CardView>(R.id.cv_delivered)
        val cvCancelled = statedialog.findViewById<CardView>(R.id.cv_cancelled)

        cvPending.setOnClickListener {
            orden.order.estado = "Pendiente"
            val orden = Orders(orden.order.orderId,orden.order.fecha,orden.order.cliente,orden.order.estado,orden.order.total)
            ordersViewModel.updateOrder(orden)
            initRecyclerView()
            statedialog.dismiss()

        }

        cvInPreparation.setOnClickListener {
            orden.order.estado = "En preparación"
            val orden = Orders(orden.order.orderId,orden.order.fecha,orden.order.cliente,orden.order.estado,orden.order.total)
            ordersViewModel.updateOrder(orden)
            initRecyclerView()
            statedialog.dismiss()

        }

        cvReady.setOnClickListener {
            orden.order.estado = "Listo para entregar"
            val orden = Orders(orden.order.orderId,orden.order.fecha,orden.order.cliente,orden.order.estado,orden.order.total)
            ordersViewModel.updateOrder(orden)
            initRecyclerView()
            statedialog.dismiss()

        }

        cvDelivered.setOnClickListener {
            orden.order.estado = "Entregado"
            val orden = Orders(orden.order.orderId,orden.order.fecha,orden.order.cliente,orden.order.estado,orden.order.total)
            ordersViewModel.updateOrder(orden)
            initRecyclerView()
            statedialog.dismiss()

        }

        cvCancelled.setOnClickListener {
            orden.order.estado = "Cancelado"
            val orden = Orders(orden.order.orderId,orden.order.fecha,orden.order.cliente,orden.order.estado,orden.order.total)
            ordersViewModel.updateOrder(orden)
            initRecyclerView()
            statedialog.dismiss()

        }

        statedialog.show()
    }

    override fun showOrderDialog(orden: orderWithDishes) {
        TODO("Not yet implemented")
    }
}