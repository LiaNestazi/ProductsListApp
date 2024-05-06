package com.productslistapp.di

import com.productslistapp.domain.usecase.GetAllCategoriesUseCase
import com.productslistapp.domain.usecase.GetProductDetailsUseCase
import com.productslistapp.domain.usecase.GetProductsByCategoryUseCase
import com.productslistapp.domain.usecase.GetProductsUseCase
import com.productslistapp.domain.usecase.SearchProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetProductDetailsUseCase> {
        GetProductDetailsUseCase(productsRepository = get())
    }
    factory<GetProductsByCategoryUseCase> {
        GetProductsByCategoryUseCase(productsRepository = get())
    }
    factory<GetProductsUseCase> {
        GetProductsUseCase(productsRepository = get())
    }
    factory<SearchProductUseCase> {
        SearchProductUseCase(productsRepository = get())
    }
    factory<GetAllCategoriesUseCase> {
        GetAllCategoriesUseCase(productsRepository = get())
    }
}