package com.productslistapp.domain.repository

import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.models.Product
import com.productslistapp.domain.models.RequestParams

interface ProductsRepository {
    suspend fun getAllProduct(requestParams: RequestParams) : ResponseResult
    suspend fun searchProduct(searchRequest: String) : ResponseResult
    suspend fun getProductsByCategory(category: String) : ResponseResult
    suspend fun getProductDetails(id: Int) : Product
    suspend fun getAllCategories() : List<String>
}