package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredientCrossRef
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredientQuantities
import com.york_ruve.quickbitemaneger.Data.Relations.dishWithIngredients

@Dao
interface dishesDao {

    @Transaction
    @Query("SELECT * FROM dishes")
    suspend fun getAllDishes(): List<dishWithIngredients>

    @Query("Select * from dish_ingredient_cross_ref where dishId = :dishId")
    suspend fun getIngredientsByDishId(dishId: Int): List<DishIngredientCrossRef>

    @Insert
    suspend fun insertDish(dish: dishEntity)

    @Insert
    suspend fun insertDishWithIngredients(crossRef: DishIngredientCrossRef)
}