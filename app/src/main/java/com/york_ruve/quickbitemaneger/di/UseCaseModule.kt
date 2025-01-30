package com.york_ruve.quickbitemaneger.di

import com.york_ruve.quickbitemaneger.Domain.Repository.IDishIngredients
import com.york_ruve.quickbitemaneger.Domain.Repository.IDishesRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IIngredientsRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersDish
import com.york_ruve.quickbitemaneger.Domain.Repository.IOrdersRepository
import com.york_ruve.quickbitemaneger.Domain.Repository.ISaleRepository
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getAllDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Dish.getAllDishesWithIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.getDishIngredientsByIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.DishIngredients.insertDishIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.getAllIngredientsUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.getIngredientsByDishIdUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Ingredient.insertIngredientUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.deleteOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getAllOrdersWithDishesUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.getOrdersByStateUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.insertOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.Orders.updateOrdersUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.deleteOrderDishUseCase
import com.york_ruve.quickbitemaneger.Domain.UsesCases.OrdersDish.insertOrderDishUseCase
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

    @Provides
    fun provideGetAllDishesUseCase(dishRepository: IDishesRepository): getAllDishesUseCase {
        return getAllDishesUseCase(dishRepository)
    }


    @Provides
    fun provideInsertDishIngredientsUseCase(dishRepository: IDishIngredients): insertDishIngredientsUseCase {
        return insertDishIngredientsUseCase(dishRepository)
    }

    @Provides
    fun provideGetAllOrdersUseCase(ordersRepository: IOrdersRepository): getAllOrdersUseCase {
        return getAllOrdersUseCase(ordersRepository)
    }

    @Provides
    fun provideInsertOrderUseCase(ordersRepository: IOrdersRepository): insertOrdersUseCase {
        return insertOrdersUseCase(ordersRepository)
    }

    @Provides
    fun provideUpdateOrderUseCase(ordersRepository: IOrdersRepository): updateOrdersUseCase {
        return updateOrdersUseCase(ordersRepository)

    }

    @Provides
    fun provideDeleteOrderUseCase(ordersRepository: IOrdersRepository): deleteOrdersUseCase {
        return deleteOrdersUseCase(ordersRepository)

    }

    @Provides
    fun provideGetOrdersByStateUseCase(ordersRepository: IOrdersRepository): getOrdersByStateUseCase {
        return getOrdersByStateUseCase(ordersRepository)
    }

    @Provides
    fun provideInsertOrderDishUseCase(ordersRepository: IOrdersDish): insertOrderDishUseCase {
        return insertOrderDishUseCase(ordersRepository)
    }

    @Provides
    fun provideDeleteOrderDishUseCase(ordersRepository: IOrdersDish): deleteOrderDishUseCase {
        return deleteOrderDishUseCase(ordersRepository)

    }

    @Provides
    fun provideGetAllOrdersWithDishesUseCase(ordersRepository: IOrdersRepository): getAllOrdersWithDishesUseCase {
        return getAllOrdersWithDishesUseCase(ordersRepository)
    }

    @Provides
    fun provideInsertIngredientsUseCase(ingredientsRepository: IIngredientsRepository): insertIngredientUseCase {
        return insertIngredientUseCase(ingredientsRepository)
    }

    @Provides
    fun providesGetAllDishesWithIngredientsUseCase(ingredients: IDishesRepository):getAllDishesWithIngredientsUseCase{
        return getAllDishesWithIngredientsUseCase(ingredients)
    }

    @Provides
    fun provideGetAllIngredientsUseCase(ingredientsRepository: IIngredientsRepository): getAllIngredientsUseCase {
        return getAllIngredientsUseCase(ingredientsRepository)
    }

    @Provides
    fun provideGetIngredientsByDishIdUseCase(ingredientsRepository: IDishesRepository): getIngredientsByDishIdUseCase {
        return getIngredientsByDishIdUseCase(ingredientsRepository)
    }

    @Provides
    fun providesGetDishIngredientsByIdUseCase(dishIngredientsRepository: IDishIngredients): getDishIngredientsByIdUseCase {
        return getDishIngredientsByIdUseCase(dishIngredientsRepository)
    }
}