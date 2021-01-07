package com.example.greenthumb_tester

data class CountryResponse(
    val data: List<CountryData>,
    val message: String,
    val status: String
)