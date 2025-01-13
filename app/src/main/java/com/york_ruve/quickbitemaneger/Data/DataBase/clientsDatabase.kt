package com.york_ruve.quickbitemaneger.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.clientsDao
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity

@Database(entities = [ClientsEntity::class], version = 1, exportSchema = false)
abstract class clientsDatabase:RoomDatabase() {
    abstract fun clientsDao():clientsDao

    companion object{
        fun getInstance(context: Context): clientsDatabase {
            return Room.databaseBuilder(
                context,
                clientsDatabase::class.java,
                "clients_database"
            ).build()
        }
    }
}