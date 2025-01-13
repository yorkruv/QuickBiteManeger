package com.york_ruve.quickbitemaneger.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Entities.ordersEntity

@Database(entities = [ordersEntity::class], version = 1, exportSchema = false)
abstract class ordersDatabase: RoomDatabase() {
    abstract fun ordersDao(): ordersDao

    companion object{
        fun getInstance(context: Context): ordersDatabase {
            return Room.databaseBuilder(
                context,
                ordersDatabase::class.java,
                "orders_database"
            ).build()
        }
    }

}