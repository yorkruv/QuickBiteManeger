package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

@Dao
interface ingredientsDao {
    @Query("SELECT * FROM ingredients")
    suspend fun getAllIngredients(): List<ingredientEntity>

    @Query("Update ingredients set stock = stock - :quantity where ingredientId = :ingredientId")
    suspend fun updateIngredientStock(ingredientId: Int, quantity: Double)

    @Insert
    suspend fun insertIngredient(ingredient: ingredientEntity)

    @Query("Select * from ingredients where ingredientId = :ingredientId")
    suspend fun getIngredientById(ingredientId: Int): ingredientEntity

}