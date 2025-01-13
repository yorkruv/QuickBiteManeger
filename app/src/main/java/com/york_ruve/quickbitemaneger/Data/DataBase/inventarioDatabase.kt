package com.york_ruve.quickbitemaneger.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredientCrossRef
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity

@Database(
    entities = [
        dishEntity::class,
        ingredientEntity::class,
        DishIngredientCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class inventarioDatabase : RoomDatabase() {
    abstract fun dishesDao(): dishesDao
    abstract fun ingredientsDao(): ingredientsDao

    companion object {
        fun getInstance(context: Context): inventarioDatabase {
            return Room.databaseBuilder(
                context,
                inventarioDatabase::class.java,
                "inventario_database"
            ).build()
        }

    }
}