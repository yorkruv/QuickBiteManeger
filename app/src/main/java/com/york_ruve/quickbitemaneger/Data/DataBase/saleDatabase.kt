package com.york_ruve.quickbitemaneger.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.Entities.SalesEntity

@Database(entities = [SalesEntity::class], version = 1, exportSchema = false)
abstract class saleDatabase: RoomDatabase() {

    abstract fun salesDao(): salesDao



}