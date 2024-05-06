package com.productslistapp.domain.usecase

import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.repository.ProductsRepository

class SearchProductUseCase(private val productsRepository: ProductsRepository) {
    suspend fun execute(searchRequest: String) : ResponseResult {
        return productsRepository.searchProduct(searchRequest)
    }
}