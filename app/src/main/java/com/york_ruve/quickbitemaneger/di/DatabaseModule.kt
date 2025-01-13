package com.york_ruve.quickbitemaneger.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.DataBase.saleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSaleDatabase(@ApplicationContext context: Context): saleDatabase {
        return Room.databaseBuilder(
            context,
            saleDatabase::class.java,
            "database_name"
        ).build()
    }

    @Provides
    fun provideDao(database: saleDatabase): salesDao {
        return database.salesDao()
    }
}