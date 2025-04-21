package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

@Dao
interface ingredientsDao {
    @Query("SELECT * FROM ingredients ORDER BY ingredientId DESC")
    suspend fun getAllIngredients(): List<ingredientEntity>

    @Query("Update ingredients set stock = stock - :quantity where ingredientId = :ingredientId")
    suspend fun updateIngredientStock(ingredientId: Int?, quantity: Double?)

    @Query("SELECT * FROM ingredients WHERE stock <= criticalQuantity")
    suspend fun getCriticalIngredients(): List<ingredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: ingredientEntity):Long

    @Query("Select * from ingredients where ingredientId = :ingredientId")
    suspend fun getIngredientById(ingredientId: Int): ingredientEntity

    @Update
    suspend fun updateIngredient(ingredient: ingredientEntity)

    @Delete
    suspend fun deleteIngredient(ingredient: ingredientEntity)
}