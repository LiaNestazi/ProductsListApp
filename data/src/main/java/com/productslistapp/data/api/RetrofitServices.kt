package com.productslistapp.data.api

import com.productslistapp.data.api.models.HttpCall
import com.productslistapp.domain.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {
    @GET("products")
    fun getProductsList(@Query("skip") skip: Int,
                        @Query("limit") limit: Int
    ): Call<HttpCall>
    @GET("/products/{id}")
    fun getProductById(
        @Path("id") id:Int
    ):Call<Product>
    @GET("/products/search")
    fun searchByQuery(
        @Query("q") query: String
    ): Call<HttpCall>
    @GET("/products/categories")
    fun getAllCategories(): Call<List<String>>
    @GET("/products/category/{category}")
    fun getProductsByCategory(
        @Path("category") category:String
    ):Call<HttpCall>
}