package com.productslistapp.domain.usecase

import com.productslistapp.domain.models.Product
import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.repository.ProductsRepository

class GetProductDetailsUseCase(private val productsRepository: ProductsRepository) {
    suspend fun execute(id: Int) : Product{
        return productsRepository.getProductDetails(id)
    }
}