package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Domain.Model.Sales
import com.york_ruve.quickbitemaneger.Presentation.utils.saleWithDishQuantity
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemSalesBinding

class salesAdapter(private val sales: List<saleWithDishQuantity>): RecyclerView.Adapter<salesAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemSalesBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_sales, parent, false))
    }

    override fun getItemCount(): Int = sales.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sale = sales[position]
        holder.binding.tvIdDate.text = "#${sale.sale.id} - ${sale.sale.fecha}"
        holder.binding.tvClient.text = sale.sale.cliente
        holder.binding.tvTotal.text = "$${sale.sale.total}"
        holder.binding.lvDishes.adapter = dishWithQuantityAdapter(holder.itemView.context, sale.dishQuantity)
    }
}