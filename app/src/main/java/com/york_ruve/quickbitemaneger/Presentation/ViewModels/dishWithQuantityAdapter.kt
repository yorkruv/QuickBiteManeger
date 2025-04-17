package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.compose.ui.layout.Layout
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithQuantity
import com.york_ruve.quickbitemaneger.R

class dishWithQuantityAdapter(
    context: Context,
    private val dishWithQuantityList: List<dishWithQuantity>
) :
    ArrayAdapter<dishWithQuantity>(context, 0, dishWithQuantityList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_dish_with_quantity, parent, false)
        val dishWithQuantity = dishWithQuantityList[position]
        view.findViewById<TextView>(R.id.tv_dish_name).text = dishWithQuantity.dish.nombre
        view.findViewById<TextView>(R.id.tv_dish_quantity).text = "x${dishWithQuantity.quantity}"

        return view
    }
}