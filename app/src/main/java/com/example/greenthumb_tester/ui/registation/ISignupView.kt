package com.example.greenthumb_tester.ui.registation

import com.example.greenthumb_tester.model.registration.SignupResponse
import com.example.greenthumb_tester.model.login.Error
import com.google.gson.JsonObject
import retrofit2.Response

interface ISignupView{

    fun onSignupSuccess(loginBase: SignupResponse)
    fun onSignupError(error: Error)

    fun countrySpinner(response: Response<JsonObject>)

    fun stateSpinner(response: Response<JsonObject>)
    fun citySpinner(response: Response<JsonObject>)

}