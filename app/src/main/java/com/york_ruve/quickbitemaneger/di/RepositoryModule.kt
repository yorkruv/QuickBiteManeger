package com.york_ruve.quickbitemaneger.di

import com.york_ruve.quickbitemaneger.Data.Dao.salesDao
import com.york_ruve.quickbitemaneger.Data.Repository.saleRepository
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

}