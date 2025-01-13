package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.insertIngredientUseCase
import kotlinx.coroutines.launch

class ingredientViewModel(
    private val insertIngredientUseCase: insertIngredientUseCase
):ViewModel() {
    private val _ingredients = MutableLiveData<List<Ingredients>>()
    val ingredients : LiveData<List<Ingredients>> = _ingredients

    fun insertIngredient(ingredients: Ingredients){
        viewModelScope.launch {
            insertIngredientUseCase(ingredients)
        }

    }
}