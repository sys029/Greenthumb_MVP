package com.example.greenthumb_tester.model.registration

import com.example.greenthumb_tester.model.registration.CountryData

data class CountryResponse(
    val data: List<CountryData>,
    val message: String,
    val status: String
)