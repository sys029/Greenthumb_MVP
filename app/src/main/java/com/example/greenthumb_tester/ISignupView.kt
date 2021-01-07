package com.example.greenthumb_tester

import com.google.gson.JsonObject
import retrofit2.Response

interface ISignupView{

    fun onSignupSuccess(loginBase:SignupResponse)
    fun onSignupError(error: Error)

    fun countrySpinner(response: Response<JsonObject>)

    fun stateSpinner(response: Response<JsonObject>)
    fun citySpinner(response: Response<JsonObject>)

}