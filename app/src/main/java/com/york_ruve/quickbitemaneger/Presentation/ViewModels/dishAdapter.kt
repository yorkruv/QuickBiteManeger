package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Presentation.utils.OnDishClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemDishesBinding

class dishAdapter(private val dishlist:List<DishWithIngredients>, private val onDishClickListener: OnDishClickListener): RecyclerView.Adapter<dishAdapter.ViewHolder>() {



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemDishesBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dishAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dishes, parent, false))
    }

    override fun onBindViewHolder(holder: dishAdapter.ViewHolder, position: Int) {
        val dish = dishlist[position]
        holder.binding.tvName.text = dish.dish.nombre
        holder.binding.tvPrice.text = dish.dish.precio.toString()
        holder.binding.tvIngredients.text = dish.ingredients.joinToString { it.nombre }
        holder.binding.ivEdit.setOnClickListener { onDishClickListener.onEditDishClick(dish) }
        holder.binding.ivDelete.setOnClickListener { onDishClickListener.onDeleteDishClick(dish) }

    }

    override fun getItemCount(): Int = dishlist.size
}