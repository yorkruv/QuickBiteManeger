package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Presentation.utils.OnDishIngredientsClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemDishingredientBinding

class DishIngredientAdapter(
    private val dishIngredients: List<IngredientsWithQuantity>,
    private val listener: OnDishIngredientsClickListener,
    private val dishId: Int
) : RecyclerView.Adapter<DishIngredientAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDishingredientBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dishingredient, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dishIngredient = dishIngredients[position]
        holder.binding.tvNameIngredient.text = dishIngredient.ingredient.nombre
        holder.binding.etQuantity.setText(dishIngredient.quantity.toString())
        holder.binding.etQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val newQuantity = p0.toString().toDoubleOrNull() ?: 0.0
                if (newQuantity != dishIngredient.quantity) {
                    listener.OnQuantityChanged(dishId, newQuantity, dishIngredient.ingredient.ingredientId!!)
                }
            }

        })
        holder.binding.ivDelete.setOnClickListener {
            listener.OnDeleteDishIngredientClick(dishId, dishIngredient.ingredient.ingredientId!!, dishIngredient.quantity)
        }
    }

    override fun getItemCount(): Int = dishIngredients.size
}