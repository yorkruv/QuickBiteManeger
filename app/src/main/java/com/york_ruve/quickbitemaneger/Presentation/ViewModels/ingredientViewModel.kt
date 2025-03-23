package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.getDishIngredientsByIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.getAllIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.getIngredientByIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.insertIngredientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.subtractIngredientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.updateIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ingredientViewModel @Inject constructor(
    private val insertIngredientUseCase: insertIngredientUseCase,
    private val getAllIngredientsUseCase: getAllIngredientsUseCase,
    private val subtractIngredientUseCase: subtractIngredientUseCase,
    private val getDishIngredientsByIdUseCase: getDishIngredientsByIdUseCase,
    private val getIngredientByIdUseCase: getIngredientByIdUseCase,
    private val updateIngredientUseCase: updateIngredientUseCase
) : ViewModel() {
    private val _ingredients = MutableLiveData<List<Ingredients>>()
    val ingredients: LiveData<List<Ingredients>> = _ingredients

    private val _ingredientsCriticals = MutableLiveData<Int>()
    val ingredientsCriticals: LiveData<Int> = _ingredientsCriticals

    private val _dishIngredients = MutableLiveData<List<DishIngredient>>()
    val dishIngredients: LiveData<List<DishIngredient>> = _dishIngredients

    private val _ingredient = MutableLiveData<Ingredients>()
    val ingredient: LiveData<Ingredients> = _ingredient

    fun insertIngredient(ingredients: Ingredients) {
        viewModelScope.launch {
            insertIngredientUseCase(ingredients)
        }
    }

    fun getAllIngredients() {
        viewModelScope.launch {
            val ingredients = getAllIngredientsUseCase()
            _ingredients.postValue(ingredients)
            var aux = 0
            for (ingredient in ingredients) {
                if (ingredient.stock < ingredient.criticalQuantity) {
                    aux += 1
                }
            }
            _ingredientsCriticals.postValue(aux)
        }

    }

    fun SubstractIngredientStock(ingredientId: Int?, quantity: Double?) {
        viewModelScope.launch {
            subtractIngredientUseCase(ingredientId, quantity)
        }
    }

    fun getDishIngredientsById(dishId: Int?, ingredientId: List<Int?>) {
        viewModelScope.launch(Dispatchers.IO) {
            val dishIngredient = getDishIngredientsByIdUseCase(dishId, ingredientId)
            withContext(Dispatchers.Main) {
                _dishIngredients.value = dishIngredient
            }
        }
    }

    fun getIngredientById(ingredientId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredient = getIngredientByIdUseCase(ingredientId)
            withContext(Dispatchers.Main) {
                _ingredient.value = ingredient
            }
        }
    }

    fun updateIngredient(ingredient: Ingredients) {
        viewModelScope.launch {
            updateIngredientUseCase(ingredient)
        }
    }
}