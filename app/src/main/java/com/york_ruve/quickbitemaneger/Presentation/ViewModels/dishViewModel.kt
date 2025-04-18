package com.york_ruve.quickbitemaneger.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity
import com.york_ruve.quickbitemaneger.Domain.Model.Dish
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.deleteDishUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getAllDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getAllDishesWithIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getIngredientsWithQuantityUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.insertDishUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.deleteDishIngredientsByDishIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.deleteDishIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.insertDishIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.updateDishIngredientQuantityUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.getIngredientsByDishIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class dishViewModel @Inject constructor(
    private val getAllDishesUseCase: getAllDishesUseCase,
    private val getAllDishesWithIngredientsUseCase: getAllDishesWithIngredientsUseCase,
    private val insertDishIngredientsUseCase: insertDishIngredientsUseCase,
    private val insertDishUseCase: insertDishUseCase,
    private val deleteDishIngredientsUseCase: deleteDishIngredientsUseCase,
    private val getIngredientsByDishIdUseCase: getIngredientsByDishIdUseCase,
    private val getIngredientsWithQuantityUseCase: getIngredientsWithQuantityUseCase,
    private val deleteDishIngredientsByDishIdUseCase: deleteDishIngredientsByDishIdUseCase,
    private val deleteDishUseCase: deleteDishUseCase,
    private val updateDishIngredientQuantityUseCase: updateDishIngredientQuantityUseCase
) : ViewModel() {
    private val _dish = MutableLiveData<List<Dish>>()
    val dish: LiveData<List<Dish>> = _dish

    private val _dishIngredient = MutableLiveData<List<DishWithIngredients>>()
    val dishIngredient: LiveData<List<DishWithIngredients>> = _dishIngredient

    private val _IngredientsByDishId = MutableLiveData<DishWithIngredients?>()
    val IngredientsByDishId: LiveData<DishWithIngredients?> = _IngredientsByDishId

    private val _IngredientsWithQuantity = MutableLiveData<List<IngredientsWithQuantity>>()
    val IngredientsWithQuantity: LiveData<List<IngredientsWithQuantity>> = _IngredientsWithQuantity

    private val _newId = MutableLiveData<Long>()
    val newId:LiveData<Long> get() = _newId

    fun loadDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            val dishes = getAllDishesUseCase()
            _dish.postValue(dishes)
        }
    }

    fun insertDish(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = insertDishUseCase(dish)
            loadDishes()
            getAllDishesWithIngredients()
            _newId.postValue(id)
        }
    }

    fun insertDishIngredients(dish: DishIngredient) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDishIngredientsUseCase(dish)
        }
    }

    fun deleteDishIngredient(dish: DishIngredient) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDishIngredientsUseCase(dish)
        }
    }

    fun deleteDishIngredientsByDishId(dishId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDishIngredientsByDishIdUseCase(dishId)
        }
    }

    fun getAllDishesWithIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            val dishIngredient = getAllDishesWithIngredientsUseCase()
            _dishIngredient.postValue(dishIngredient)
        }
    }

    fun getIngredientsWithQuantity(dishId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredients = getIngredientsWithQuantityUseCase(dishId)
            withContext(Dispatchers.Main) {
                _IngredientsWithQuantity.value = ingredients
            }
        }
    }

    suspend fun getIngredientsWithQuantityAux(dishId: Int): List<IngredientsWithQuantity> {
        return getIngredientsWithQuantityUseCase(dishId)
    }

    fun getIngredientsByDishId(dishId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredients = getIngredientsByDishIdUseCase(dishId)
            withContext(Dispatchers.Main) {
                _IngredientsByDishId.value = ingredients
            }
        }
    }

    fun deleteDish(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDishUseCase(dish)
        }
    }

    fun updateDishIngredientQuantity(dishId: Int, ingredientId: Int, newQuantity: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            updateDishIngredientQuantityUseCase(dishId, ingredientId, newQuantity)
        }
    }
}