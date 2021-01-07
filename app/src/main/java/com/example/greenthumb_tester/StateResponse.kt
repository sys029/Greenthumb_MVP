package com.example.greenthumb_tester

data class StateResponse(
    val data: List<StateData>,
    val message: String,
    val status: String
)
