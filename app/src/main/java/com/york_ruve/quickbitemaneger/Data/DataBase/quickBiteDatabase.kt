package com.york_ruve.quickbitemaneger.Data.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.clientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishIngredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDishDao
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity
import com.york_ruve.quickbitemaneger.Data.Entities.DishIngredient
import com.york_ruve.quickbitemaneger.Data.Entities.OrdersDish
import com.york_ruve.quickbitemaneger.Data.Entities.SalesEntity
import com.york_ruve.quickbitemaneger.Data.Entities.dishEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ingredientEntity
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity

@Database(
    entities = [SalesEntity::class, ordersEntity::class, dishEntity::class,
        OrdersDish::class, ingredientEntity::class, ClientsEntity::class,
               DishIngredient::class],
    version = 1,
    exportSchema = false
)
abstract class quickBiteDatabase:RoomDatabase(){
    abstract fun salesDao(): salesDao
    abstract fun ordersDao(): ordersDao
    abstract fun dishDao(): dishesDao
    abstract fun ordersDishDao(): ordersDishDao
    abstract fun ingredientsDao(): ingredientsDao
    abstract fun clientsDao(): clientsDao
    abstract fun dishIngredientsDao(): dishIngredientsDao
}