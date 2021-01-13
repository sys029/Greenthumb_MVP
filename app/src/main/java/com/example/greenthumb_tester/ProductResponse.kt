package com.example.greenthumb_tester

data class ProductResponse(
    val `data`: List<ProductData>,
    val message: String,
    val status: String
)