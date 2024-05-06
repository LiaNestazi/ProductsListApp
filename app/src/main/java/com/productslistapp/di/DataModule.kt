package com.productslistapp.di

import com.productslistapp.data.repository.ProductsRepositoryImpl
import com.productslistapp.domain.repository.ProductsRepository
import org.koin.dsl.module

val dataModule = module {
    single<ProductsRepository> {
        ProductsRepositoryImpl()
    }

}