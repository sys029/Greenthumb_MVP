package com.example.greenthumb_tester.model.registration

import com.example.greenthumb_tester.model.registration.CityData

data class CityResponse(
    val data: List<CityData>,
    val message: String,
    val status: String
)
