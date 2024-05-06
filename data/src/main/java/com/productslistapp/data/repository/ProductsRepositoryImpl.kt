package com.productslistapp.data.repository

import com.productslistapp.data.api.models.HttpCall
import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.data.api.Common
import com.productslistapp.domain.models.Product
import com.productslistapp.domain.models.RequestParams
import com.productslistapp.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.await

class ProductsRepositoryImpl : ProductsRepository {

    override suspend fun getAllProduct(requestParams: RequestParams): ResponseResult {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getProductsList(requestParams.skip, requestParams.limit).await()
        }
        return mapToDomain(httpCall = job.await())
    }

    override suspend fun searchProduct(searchRequest: String): ResponseResult {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.searchByQuery(searchRequest).await()
        }
        return mapToDomain(httpCall = job.await())
    }

    override suspend fun getProductsByCategory(category: String): ResponseResult {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getProductsByCategory(category).await()
        }
        return mapToDomain(httpCall = job.await())
    }

    override suspend fun getProductDetails(id: Int): Product {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getProductById(id).await()
        }
        return job.await()
    }

    override suspend fun getAllCategories(): List<String> {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getAllCategories().await()
        }
        return job.await()
    }

    private fun mapToDomain(error: String? = null, httpCall: HttpCall): ResponseResult{
        return ResponseResult(errorMessage = error, totalValue = httpCall.total, productsList = httpCall.products)
    }

}