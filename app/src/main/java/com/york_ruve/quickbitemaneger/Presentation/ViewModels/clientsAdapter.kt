package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Presentation.utils.OnClientClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemDishesBinding

class clientsAdapter(private val clientList: List<Clients>,private val listener: OnClientClickListener): RecyclerView.Adapter<clientsAdapter.ViewHolder>() {
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val binding = ItemDishesBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dishes, parent, false))
    }

    override fun getItemCount(): Int = clientList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = clientList[position]
        holder.binding.tvName.text = client.nombre
        holder.binding.tvPrice.text = client.direccion
        holder.binding.tvIngredients.text = client.telefono
        holder.binding.ivEdit.setOnClickListener {
            listener.onEditClientClick(client)
        }
        holder.binding.ivDelete.setOnClickListener {
            listener.onDeleteClientClick(client)
        }
    }
}