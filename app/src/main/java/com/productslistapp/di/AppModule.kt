package com.productslistapp.di

import com.productslistapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel>{
        MainViewModel(
            getProductDetailsUseCase = get(),
            getProductsByCategory = get(),
            getProductsUseCase = get(),
            searchProductUseCase = get(),
            getAllCategoriesUseCase = get()
        )
    }
}