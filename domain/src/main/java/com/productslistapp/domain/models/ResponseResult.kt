package com.productslistapp.domain.models

data class ResponseResult(
    var errorMessage: String? = null,
    var totalValue: Int? = null,
    var productsList: MutableList<Product>? = null,
)