package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Presentation.utils.OnIngredientClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemIngredientsBinding

class ingredientAdapter(private val ingredientlist:List<Ingredients>, private val onIngredientClickListener: OnIngredientClickListener): RecyclerView.Adapter<ingredientAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemIngredientsBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ingredientAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_ingredients, parent, false))
    }

    override fun onBindViewHolder(holder: ingredientAdapter.ViewHolder, position: Int) {
        val ingredient = ingredientlist[position]
        holder.binding.tvName.text = ingredient.name
        holder.binding.tvQuatity.text = ingredient.stock.toString()
        holder.binding.tvUnit.text = ingredient.unit
        holder.binding.ivEdit.setOnClickListener {
            onIngredientClickListener.onEditIngredientClick(ingredient)
        }
        holder.binding.ivDelete.setOnClickListener {
            onIngredientClickListener.onDeleteIngredientClick(ingredient)
        }
    }

    override fun getItemCount(): Int = ingredientlist.size

}