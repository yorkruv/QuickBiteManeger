package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Data.Relations.orderWithDishes
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemOrdersBinding

class ordersAdapter(
    private val ordersList: List<orderWithDishes>,
    private val listener: OnOrderClickListener
) : RecyclerView.Adapter<ordersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOrdersBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_orders, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orden = ordersList[position]
        val context = holder.binding.root.context
        val resources = context.resources

        holder.binding.tvPrecioTotal.text = orden.order.total.toString()
        holder.binding.tvState.text = orden.order.estado
        holder.binding.tvPlatosDelOrden.text = orden.dishes.joinToString { it.nombre }
        holder.binding.tvNameClient.text = orden.order.cliente
        val color = when (orden.order.estado) {
            resources.getString(R.string.pending) -> ContextCompat.getColor(holder.binding.root.context, R.color.yellowPending)
            resources.getString(R.string.in_preparation) -> ContextCompat.getColor(holder.binding.root.context, R.color.blueInPreparation)
            resources.getString(R.string.ready) -> ContextCompat.getColor(holder.binding.root.context, R.color.greenReady)
            resources.getString(R.string.delivered) -> ContextCompat.getColor(holder.binding.root.context, R.color.grayDelivered)
            resources.getString(R.string.cancelled) -> ContextCompat.getColor(holder.binding.root.context, R.color.redCancelled)
            else -> ContextCompat.getColor(holder.binding.root.context, R.color.black)
        }
        holder.binding.cvState.setCardBackgroundColor(color)
        holder.binding.root.setOnClickListener {
            listener.onOrderClick(orden)
        }
        holder.binding.cvState.setOnClickListener {
            listener.onStateClick(orden)
        }
    }


    override fun getItemCount(): Int = ordersList.size

}