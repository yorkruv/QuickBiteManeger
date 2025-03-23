package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnIngredientClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.DialogEditIngredientBinding
import com.york_ruve.quickbitemaneger.databinding.FragmentInventoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoryFragment : Fragment(), OnIngredientClickListener {

    private lateinit var binding: FragmentInventoryBinding

    private val inventoryViewModel: ingredientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()
    }

    private fun setListeners() {
        binding.btnAddIngredient.setOnClickListener {
            val ingredient = Ingredients(null, "", 0.0, "", 0.0)
            inventoryViewModel.insertIngredient(ingredient)
            Toast.makeText(requireContext(), getString(R.string.ins_ingredients), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        inventoryViewModel.getAllIngredients()
        inventoryViewModel.ingredients.observe(viewLifecycleOwner) {
            binding.rvIngredients.layoutManager = LinearLayoutManager(this.context)
            binding.rvIngredients.adapter = ingredientAdapter(it,this)
        }
    }

    override fun onEditIngredientClick(ingredient: Ingredients) {
        val dialog = Dialog(requireContext())
        val binding = DialogEditIngredientBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.etName.setText(ingredient.name)
        binding.etQuantity.setText(ingredient.stock.toString())
        binding.etUnit.setText(ingredient.unit)
        binding.etCriticalQuantity.setText(ingredient.criticalQuantity.toString())
        binding.btnSaveIngredient.setOnClickListener {
            val ingredient = Ingredients(
                id = ingredient.id,
                name = binding.etName.text.toString(),
                stock = binding.etQuantity.text.toString().toDouble(),
                unit = binding.etUnit.text.toString(),
                criticalQuantity = binding.etCriticalQuantity.text.toString().toDouble()
            )
            inventoryViewModel.updateIngredient(ingredient)
            dialog.dismiss()
        }
        binding.ivClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDeleteIngredientClick(ingredient: Ingredients) {
        inventoryViewModel.deleteIngredient(ingredient)
    }

}