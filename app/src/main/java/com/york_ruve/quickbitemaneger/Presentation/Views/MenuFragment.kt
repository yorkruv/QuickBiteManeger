package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.DishIngredientAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.addIngredientAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.dishViewModel
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnAddIngredientClickListener
import com.york_ruve.quickbitemaneger.Presentation.utils.OnDishClickListener
import com.york_ruve.quickbitemaneger.Presentation.utils.OnDishIngredientsClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.DialogAddingredientBinding
import com.york_ruve.quickbitemaneger.databinding.DialogEditDishBinding
import com.york_ruve.quickbitemaneger.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment(), OnDishClickListener, OnDishIngredientsClickListener,
    OnAddIngredientClickListener {
    private lateinit var binding: FragmentMenuBinding

    private val dishViewModel: dishViewModel by viewModels()
    private val ingredientsViewModel: ingredientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setlistener()
    }

    private fun setlistener() {
        binding.btnAddDish.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val dish = Dish(null, "", "", 0.0)
                val id = dishViewModel.insertDish(dish)
                val dishWithIngredients = dishViewModel.getIngredientsByDishId(id.toInt())
                onEditDishClick(dishWithIngredients!!)
                dishViewModel.getAllDishesWithIngredients()
                Toast.makeText(requireContext(), getString(R.string.ins_dishes), Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun initRecyclerView() {
        dishViewModel.getAllDishesWithIngredients()
        dishViewModel.dishIngredient.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerView.adapter = dishAdapter(it, this)
        }
    }

    override fun onEditDishClick(dish: DishWithIngredients) {
        val dialog = Dialog(requireContext())
        val binding = DialogEditDishBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.etName.setText(dish.dish.nombre)
        binding.etPrice.setText(dish.dish.precio.toString())
        dishViewModel.getIngredientsWithQuantity(dish.dish.dishId!!)
        dishViewModel.IngredientsWithQuantity.observe(viewLifecycleOwner) {
            binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
            binding.rvIngredients.adapter = DishIngredientAdapter(it, this, dish.dish.dishId!!)
            dishViewModel.getAllDishesWithIngredients()
        }
        binding.btnAddIngredient.setOnClickListener {
            DialogAddIngredient(dish.dish.dishId)
        }
        binding.ivClose.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnSaveDish.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val dishUpdate = Dish(
                    dish.dish.dishId,
                    binding.etName.text.toString(),
                    dish.dish.descripcion,
                    binding.etPrice.text.toString().toDouble()
                )
                dishViewModel.insertDish(dishUpdate)
                dishViewModel.getAllDishesWithIngredients()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun DialogAddIngredient(dishId: Int) {
        val dialog = Dialog(requireContext())
        val binding = DialogAddingredientBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        ingredientsViewModel.getAllIngredients()
        ingredientsViewModel.ingredients.observe(viewLifecycleOwner) {
            binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
            binding.rvIngredients.adapter = addIngredientAdapter(it, dishId, this)
        }
        binding.ivClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDeleteDishClick(dish: DishWithIngredients) {
        dishViewModel.deleteDishIngredientsByDishId(dish.dish.dishId!!)
        val Dish = Dish(dish.dish.dishId, dish.dish.nombre, dish.dish.descripcion, dish.dish.precio)
        dishViewModel.deleteDish(Dish)
        initRecyclerView()
    }

    override fun OnQuantityChanged(dishId: Int, newQuantity: Double, ingredientId: Int) {
        dishViewModel.updateDishIngredientQuantity(dishId, ingredientId, newQuantity)
    }

    override fun OnDeleteDishIngredientClick(dishId: Int, ingredientId: Int, quantity: Double) {
        val dishIngredient = DishIngredient(dishId, ingredientId, quantity)
        dishViewModel.deleteDishIngredient(dishIngredient)
        dishViewModel.getIngredientsWithQuantity(dishId)
    }

    override fun onAddIngredientClick(dishId: Int, ingredientId: Int) {
        Toast.makeText(requireContext(), getString(R.string.Added_ingredient), Toast.LENGTH_SHORT)
            .show()
        val dishIngredient = DishIngredient(dishId, ingredientId, 1.0)
        dishViewModel.insertDishIngredients(dishIngredient)
        dishViewModel.getIngredientsWithQuantity(dishId)
    }
}