package com.example.greenthumb_tester.model.registration

import com.example.greenthumb_tester.model.registration.StateData

data class StateResponse(
    val data: List<StateData>,
    val message: String,
    val status: String
)
