package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.Model.Orders
import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.addDishAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.clientsViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishOrderAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ordersViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.salesViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnAddDishClickListener
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderClickListener
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderDishClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime


@AndroidEntryPoint
class OrdersFragment : Fragment(), OnOrderClickListener, OnOrderDishClickListener,
    OnAddDishClickListener {
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var clientAdapter: ArrayAdapter<String>

    private val ordersViewModel: ordersViewModel by viewModels()
    private val clientsViewModel: clientsViewModel by viewModels()
    private val dishViewModel: dishViewModel by viewModels()
    private val salesViewModel: salesViewModel by viewModels()
    private val ingredientViewModel: ingredientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
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
        ordersViewModel.orderDish.observe(viewLifecycleOwner) {
            binding.rvOrders.layoutManager = LinearLayoutManager(this.context)
            binding.rvOrders.adapter = ordersAdapter(it, this)
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
                R.id.all -> {
                    ordersViewModel.getOrdersDish()
                }

                R.id.Pending -> {
                    ordersViewModel.getOrdersByState(requireContext().getString(R.string.pending))
                }

                R.id.in_preparation -> {
                    ordersViewModel.getOrdersByState(requireContext().getString(R.string.in_preparation))
                }
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
                R.id.ready -> {
                    ordersViewModel.getOrdersByState(requireContext().getString(R.string.ready))
                }

                R.id.Delivered -> {
                    ordersViewModel.getOrdersByState(requireContext().getString(R.string.delivered))
                }

                R.id.canceled -> {
                    ordersViewModel.getOrdersByState(requireContext().getString(R.string.cancelled))
                }
            }
            true
        }

        binding.flbAddOrder.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val orden = Orders(
                null,
                LocalDateTime.now().toString(),
                requireContext().getString(R.string.ins_orders),
                requireContext().getString(R.string.pending),
                0.0
            )


                val id = ordersViewModel.addOrder(orden)
                val ordenCreada = ordersViewModel.loadOrderDishById(id.toInt())
                showOrderDialog(ordenCreada)
            }

            ordersViewModel.loadOrders()
            ordersViewModel.orders.observe(viewLifecycleOwner) {
                initRecyclerView()
            }
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

    override fun onDeleteIconClick(dish: dishWithQuantity, orderId: Int) {
        val orderDish = OrdersDish(orderId, dish.dish.dishId!!, dish.quantity)
        ordersViewModel.deleteOrderDish(orderDish)
        ordersViewModel.loadDishWithQuantity(orderId)
    }

    override fun onQuantityChanged(dish: dishWithQuantity, orderId: Int, newQuantity: Int) {
        val orderDish = OrdersDish(orderId, dish.dish.dishId!!, newQuantity)
        ordersViewModel.addOrderDish(orderDish)
    }


    override fun showStateDialog(orden: orderWithDishes) {
        val statedialog = Dialog(requireContext())
        statedialog.setContentView(R.layout.dialog_stateorder)

        val stateMap = mapOf(
            R.id.cv_pending to requireContext().getString(R.string.pending),
            R.id.cv_in_preparation to requireContext().getString(R.string.in_preparation),
            R.id.cv_ready to requireContext().getString(R.string.ready),
            R.id.cv_delivered to requireContext().getString(R.string.delivered),
            R.id.cv_cancelled to requireContext().getString(R.string.cancelled)
        )

        stateMap.forEach { (id, state) ->
            statedialog.findViewById<CardView>(id).setOnClickListener {
                val updatedOrder = Orders(
                    id = orden.order.orderId,
                    fecha = orden.order.fecha,
                    cliente = orden.order.cliente,
                    estado = state, // Aquí asignamos el nuevo estado
                    total = orden.order.total
                )
                ordersViewModel.updateOrder(updatedOrder)

                if (state == requireContext().getString(R.string.delivered)) {
                        val sale = Sales(
                            null,
                            orden.order.orderId!!,
                            orden.order.cliente,
                            LocalDate.now().toString(),
                            orden.order.total
                        )
                        salesViewModel.addSale(sale)
                    lifecycleScope.launch {
                        val DishWithQuantity = ordersViewModel.loadDishWithQuantityAux(orden.order.orderId)
                        for (dish in DishWithQuantity) {
                        var i = 0
                        while (i < dish.quantity) {
                            val ingredients = dishViewModel.getIngredientsWithQuantityAux(dish.dish.dishId!!)
                            i+=1
                            for (ingredient in ingredients) {
                                ingredientViewModel.SubstractIngredientStock(
                                    ingredient.ingredient.ingredientId!!,
                                    ingredient.quantity
                                )
                            }
                        }
                    }
                    }

                }
                ordersViewModel.getOrdersDish()
                statedialog.dismiss()
            }
        }
        statedialog.show()
    }

    override fun showOrderDialog(orden: orderWithDishes) {
        val orderDialog = Dialog(requireContext())
        orderDialog.setContentView(R.layout.dialog_order)

        val autoComplateClient =
            orderDialog.findViewById<AutoCompleteTextView>(R.id.actv_cliente)
        val radioGroup = orderDialog.findViewById<RadioGroup>(R.id.rg_order_type)
        val tv_order_type = orderDialog.findViewById<TextView>(R.id.tv_order_type)
        val sp_tables = orderDialog.findViewById<Spinner>(R.id.sp_tables)
        val et_address = orderDialog.findViewById<EditText>(R.id.et_address)
        val iv_infoTable = orderDialog.findViewById<ImageView>(R.id.iv_infoTable)
        val rv_orderDish = orderDialog.findViewById<RecyclerView>(R.id.rv_orderDish)
        val btn_addDish = orderDialog.findViewById<MaterialButton>(R.id.btn_addDish)
        val btn_saveOrder = orderDialog.findViewById<MaterialButton>(R.id.btn_save)

        if (orden.order.cliente != requireContext().getString(R.string.ins_orders)) {
            autoComplateClient.setText(orden.order.cliente)
        }
        clientsViewModel.loadClients()
        clientsViewModel.clients.observe(viewLifecycleOwner) { clientes ->
            val nameClient = clientes.map { it.nombre }
            clientAdapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                nameClient
            )
            autoComplateClient.setAdapter(clientAdapter)
        }
        autoComplateClient.setOnItemClickListener { _, _, position, _ ->
            val selectedClient = clientAdapter.getItem(position)
            autoComplateClient.setText(selectedClient)
            clientsViewModel.loadClientByName(autoComplateClient.text.toString())
        }
        clientsViewModel.client.observe(viewLifecycleOwner) {
            if (it != null) {
                et_address.setText(it.direccion)
            }
        }

        val mesas:MutableList<String> = mutableListOf()
        val SharedPreferences =
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val n_mesa = SharedPreferences.getInt("n_tables", 0)
        for (i in 1..n_mesa) {
            mesas.add("Mesa $i")
        }
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            mesas
        )
        sp_tables.adapter = adapter
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_home_delivery -> {
                    sp_tables.visibility = View.GONE
                    et_address.visibility = View.VISIBLE
                    tv_order_type.text = requireContext().getString(R.string.address)
                    iv_infoTable.visibility = View.GONE
                }

                R.id.rb_dine_in -> {
                    sp_tables.visibility = View.VISIBLE
                    et_address.visibility = View.GONE
                    tv_order_type.text = requireContext().getString(R.string.Table)
                    iv_infoTable.visibility = View.VISIBLE
                }
            }
        }

        ordersViewModel.loadDishWithQuantity(orden.order.orderId!!)
        ordersViewModel.dishWithQuantity.observe(viewLifecycleOwner) {
            val dishAdapter = dishOrderAdapter(it, orden.order.orderId, this)
            rv_orderDish.layoutManager = LinearLayoutManager(requireContext())
            rv_orderDish.adapter = dishAdapter
        }

        btn_addDish.setOnClickListener {
            showDishDialog(orden.order.orderId)
        }

        btn_saveOrder.setOnClickListener {
            lifecycleScope.launch {
                var priceTotal = 0.0
                val DishWithQuantity = ordersViewModel.loadDishWithQuantityAux(orden.order.orderId)

                    for (dish in DishWithQuantity) {
                        priceTotal += dish.dish.precio * dish.quantity
                    }
                    val updatedOrder = Orders(
                        id = orden.order.orderId,
                        fecha = orden.order.fecha,
                        cliente = autoComplateClient.text.toString(),
                        estado = orden.order.estado,
                        total = priceTotal
                    )
                    ordersViewModel.updateOrder(updatedOrder)
                    orderDialog.dismiss()
                    initRecyclerView()

            }

        }
        orderDialog.show()
    }

    fun showDishDialog(orderId: Int) {
        val dishDialog = Dialog(requireContext())
        dishDialog.setContentView(R.layout.dialog_adddish)

        val rv_addDishOrder = dishDialog.findViewById<RecyclerView>(R.id.rv_addDishOrder)
        val iv_close = dishDialog.findViewById<ImageView>(R.id.iv_close)

        dishViewModel.loadDishes()
        dishViewModel.dish.observe(viewLifecycleOwner) {
            val addDishAdapter = addDishAdapter(it, this, orderId)
            rv_addDishOrder.layoutManager = LinearLayoutManager(requireContext())
            rv_addDishOrder.adapter = addDishAdapter

        }

        iv_close.setOnClickListener {
            dishDialog.dismiss()
        }
        dishDialog.show()
    }

    override fun onAddDishClick(dish: Dish, orderId: Int) {
        val orderDish = OrdersDish(orderId, dish.id!!, 1)
        ordersViewModel.addOrderDish(orderDish)
        ordersViewModel.loadDishWithQuantity(orderId)
        Toast.makeText(requireContext(), "Plato agregado", Toast.LENGTH_SHORT).show()
    }

}
