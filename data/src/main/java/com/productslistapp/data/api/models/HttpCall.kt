package com.productslistapp.data.api.models

import com.productslistapp.domain.models.Product

data class HttpCall(
    var products: MutableList<Product>? = null,
    var total: Int? = null,
    var skip: Int,
    var limit: Int
)