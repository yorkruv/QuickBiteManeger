package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Presentation.utils.OnAddDishClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemAdddishBinding

class addDishAdapter(private val dishList: List<Dish>, private val listener: OnAddDishClickListener, private val ordenId:Int) : RecyclerView.Adapter<addDishAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAdddishBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addDishAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_adddish, parent, false))
    }

    override fun onBindViewHolder(holder: addDishAdapter.ViewHolder, position: Int) {
        val dish = dishList[position]
        holder.binding.tvNombreDePlato.text = dish.name
        holder.binding.tvPrecioPlato.text = dish.price.toString()
        holder.binding.btnAddDish.setOnClickListener {
            listener.onAddDishClick(dish,ordenId)
        }
    }

    override fun getItemCount(): Int = dishList.size
}