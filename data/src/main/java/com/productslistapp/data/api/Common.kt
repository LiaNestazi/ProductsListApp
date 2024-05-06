package com.productslistapp.data.api

object Common {
    private val URL = "https://dummyjson.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(URL).create(RetrofitServices::class.java)
}