package com.york_ruve.quickbitemaneger.di

import android.content.Context
import androidx.room.Room
import com.york_ruve.quickbitemaneger.Data.Dao.clientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishIngredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDishDao
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.DataBase.quickBiteDatabase
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
    fun provideQuickBiteDatabase(@ApplicationContext context: Context): quickBiteDatabase {
        return Room.databaseBuilder(
            context, quickBiteDatabase::class.java, "database_quickBite"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSalesDao(database: quickBiteDatabase): salesDao {
        return database.salesDao()
    }


    @Provides
    fun provideOrdersDao(database: quickBiteDatabase): ordersDao {
        return database.ordersDao()
    }

    @Provides
    fun provideOrdersDishDao(database: quickBiteDatabase): ordersDishDao {
        return database.ordersDishDao()
    }


    @Provides
    fun provideDishesDao(database: quickBiteDatabase): dishesDao {
        return database.dishDao()
    }

    @Provides
    fun providesDishIngredientsDao(database: quickBiteDatabase): dishIngredientsDao {
        return database.dishIngredientsDao()
    }

    @Provides
    fun providesIngredientsDao(database: quickBiteDatabase): ingredientsDao {
        return database.ingredientsDao()
    }

    @Provides
    fun provideClientsDao(database: quickBiteDatabase): clientsDao {
        return database.clientsDao()
    }
}