package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient

@Dao
interface dishIngredientsDao {
    @Query("SELECT * FROM DishIngredient WHERE dishId = :dishId AND ingredientId IN (:ingredientId)")
    fun getDishIngredientsById(dishId: Int?, ingredientId: List<Int?>): List<DishIngredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDishIngredient(dishIngredient: DishIngredient)

    @Delete
    fun deleteDishIngredient(dishIngredient: DishIngredient)

    @Query("DELETE FROM DishIngredient WHERE dishId = :dishId")
    fun deleteDishIngredientsByDishId(dishId: Int)

    @Query("UPDATE DishIngredient SET quantity = :newQuantity WHERE dishId = :dishId AND ingredientId = :ingredientId")
    fun updateDishIngredientQuantity(dishId: Int, ingredientId: Int, newQuantity: Double)
}