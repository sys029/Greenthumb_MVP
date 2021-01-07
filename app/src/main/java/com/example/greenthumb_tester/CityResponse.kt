package com.example.greenthumb_tester

data class CityResponse(
    val data: List<CityData>,
    val message: String,
    val status: String
)
