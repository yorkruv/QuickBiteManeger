package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients

@Dao
interface dishesDao {

    @Query("SELECT * FROM dishes")
    suspend fun getAllDishes(): List<dishEntity>

    @Transaction
    @Query("SELECT * FROM dishes")
    suspend fun getAllDishesWithIngredients(): List<DishWithIngredients>

    @Transaction
    @Query("Select * from dishes where dishId = :dishId")
    suspend fun getIngredientsByDishId(dishId: Int): DishWithIngredients?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: dishEntity)

}