package com.example.greenthumb_tester

import com.google.gson.JsonObject
import retrofit2.Response

interface ISignupView{

    fun onSignupSuccess(loginBase:SignupResponse)
    fun onSignupError(error: Error)

    fun onCountrySuccess(response: Response<JsonObject>)
    fun onCountryError(loginBase: Error)

    fun countrySpinnerSuccess(response: Response<JsonObject>)
    fun stateSpinnerSuccess(response: Response<JsonObject>)

}