package com.york_ruve.quickbitemaneger.di

import com.york_ruve.quickbitemaneger.Data.Dao.clientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishIngredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.dishesDao
import com.york_ruve.quickbitemaneger.Data.Dao.ingredientsDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDao
import com.york_ruve.quickbitemaneger.Data.Dao.ordersDishDao
import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.Repository.clientsRepository
import com.york_ruve.quickbitemaneger.Data.Repository.dishIngredientsRepository
import com.york_ruve.quickbitemaneger.Data.Repository.dishRepository
import com.york_ruve.quickbitemaneger.Data.Repository.ingredientsRepository
import com.york_ruve.quickbitemaneger.Data.Repository.ordersDishRepository
import com.york_ruve.quickbitemaneger.Data.Repository.ordersRepository
import com.york_ruve.quickbitemaneger.Data.Repository.saleRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IClientsRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersDish
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSalesRepository(salesDao: salesDao): ISaleRepository {
        return saleRepository(salesDao)
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(ordersDao: ordersDao): IOrdersRepository {
        return ordersRepository(ordersDao)
    }

    @Provides
    @Singleton
    fun provideDishesRepository(ordersDao: dishesDao): IDishesRepository {
        return dishRepository(ordersDao)
    }

    @Provides
    @Singleton
    fun provideOrdersDishRepository(ordersDao: ordersDishDao): IOrdersDish {
        return ordersDishRepository(ordersDao)
    }

    @Provides
    @Singleton
    fun provideDishIngredientsRepository(ordersDao: dishIngredientsDao): IDishIngredients {
        return dishIngredientsRepository(ordersDao)
    }

    @Provides
    @Singleton
    fun provideIngredientsRepository(ingredientsDao: ingredientsDao): IIngredientsRepository {
        return ingredientsRepository(ingredientsDao)
    }

    @Provides
    @Singleton
    fun provideClientsRepository(clientsDao: clientsDao): IClientsRepository {
        return clientsRepository(clientsDao)
    }
}