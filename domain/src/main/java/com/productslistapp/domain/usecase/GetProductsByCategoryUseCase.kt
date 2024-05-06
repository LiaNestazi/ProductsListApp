package com.productslistapp.domain.usecase

import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.repository.ProductsRepository

class GetProductsByCategoryUseCase(private val productsRepository: ProductsRepository) {
    suspend fun execute(category: String) : ResponseResult {
        return productsRepository.getProductsByCategory(category)
    }
}