package com.example.greenthumb_tester

interface ISignupPresenter {

    fun callSignUpAPI(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        userType: Int,
        emailId: String,
        country: String,
        city: String,
        state: String
    )

    fun countryApi()

    fun stateApi(countryId : String)
    fun cityApi(stateId : String)
}