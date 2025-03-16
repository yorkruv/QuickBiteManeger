package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Presentation.utils.OnAddIngredientClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemAddingredientBinding

class addIngredientAdapter(private val ingredients: List<Ingredients>, private val dishId:Int, private val lister:OnAddIngredientClickListener) : RecyclerView.Adapter<addIngredientAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAddingredientBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_addingredient, parent, false))
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.binding.tvName.text = ingredient.name
        holder.binding.tvQuatity.text = ingredient.stock.toString()
        holder.binding.tvUnit.text = ingredient.unit
        holder.binding.btnAdd.setOnClickListener {
            lister.onAddIngredientClick(dishId,ingredient.id!!)
        }
    }
}