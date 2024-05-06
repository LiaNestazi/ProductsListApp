package com.productslistapp.domain.usecase

import com.productslistapp.domain.repository.ProductsRepository

class GetAllCategoriesUseCase(private val productsRepository: ProductsRepository) {
    suspend fun execute() : List<String> {
        return productsRepository.getAllCategories()
    }
}