package com.york_ruve.quickbitemaneger.di

import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.GetAllSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.deleteSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.getSalesByDayUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.getTotalSalesAndAmountUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.insertSalesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.sales.updateSalesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetAllSalesUseCase(saleRepository: ISaleRepository): GetAllSalesUseCase {
        return GetAllSalesUseCase(saleRepository)
    }

    @Provides
    fun provideGetTotalSalesAndAmountUseCase(saleRepository: ISaleRepository): getTotalSalesAndAmountUseCase {
        return getTotalSalesAndAmountUseCase(saleRepository)
    }

    @Provides
    fun provideGetSalesByDayUseCase(saleRepository: ISaleRepository): getSalesByDayUseCase {
        return getSalesByDayUseCase(saleRepository)
    }

    @Provides
    fun provideInsertSalesUseCase(saleRepository: ISaleRepository): insertSalesUseCase {
        return insertSalesUseCase(saleRepository)
    }

    @Provides
    fun provideUpdateSalesUseCase(saleRepository: ISaleRepository): updateSalesUseCase {
        return updateSalesUseCase(saleRepository)
    }

    @Provides
    fun provideDeleteSalesUseCase(saleRepository: ISaleRepository): deleteSalesUseCase {
        return deleteSalesUseCase(saleRepository)
    }
}