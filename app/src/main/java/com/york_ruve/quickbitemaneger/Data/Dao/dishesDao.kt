package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Relations.DishWithIngredients
import com.york_ruve.quickbitemaneger.Data.Relations.IngredientsWithQuantity

@Dao
interface dishesDao {

    @Query("SELECT * FROM dishes ORDER BY dishId DESC")
    suspend fun getAllDishes(): List<dishEntity>

    @Transaction
    @Query("SELECT * FROM dishes ORDER BY dishId DESC")
    suspend fun getAllDishesWithIngredients(): List<DishWithIngredients>

    @Transaction
    @Query("""
        Select i.*, di.quantity
        From Ingredients i
        INNER JOIN dishIngredient di ON i.ingredientId = di.ingredientId
        WHERE di.dishId = :dishId
    """)
    suspend fun getIngredientsWithQuantity(dishId: Int): List<IngredientsWithQuantity>

    @Transaction
    @Query("Select * from dishes where dishId = :dishId")
    suspend fun getIngredientsByDishId(dishId: Int): DishWithIngredients?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: dishEntity):Long

    @Delete
    suspend fun deleteDish(dish: dishEntity)
}