package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.Presentation.utils.OnOrderDishClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ItemOrderDishBinding

class dishOrderAdapter(
    private val dishList: List<dishWithQuantity>,
    private val ordenId: Int,
    private val listener: OnOrderDishClickListener
) : RecyclerView.Adapter<dishOrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOrderDishBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_order_dish, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishList[position]
        holder.binding.tvNameDish.text = dish.dish.nombre
        holder.binding.etQuantity.setText(dish.quantity.toString())
        holder.binding.etQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newQuantity = s.toString().toIntOrNull() ?: 0
                if (newQuantity != dish.quantity) {
                    listener.onQuantityChanged(dish, ordenId, newQuantity)
                }
            }
        })
        holder.binding.ivClose.setOnClickListener {
            listener.onDeleteIconClick(dish, ordenId)
        }
    }

    override fun getItemCount(): Int = dishList.size
}