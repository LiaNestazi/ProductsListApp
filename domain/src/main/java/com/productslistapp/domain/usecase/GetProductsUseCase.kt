package com.productslistapp.domain.usecase

import com.productslistapp.domain.models.RequestParams
import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend fun execute(requestParams: RequestParams) : ResponseResult {
        return productsRepository.getAllProduct(requestParams)
    }
}