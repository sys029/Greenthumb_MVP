package com.example.greenthumb_tester.ui.registation

interface ISignupPresenter {

    fun callSignUpAPI(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        userType: Int,
        emailId: String,
        country: Int,
        city: Int,
        state: Int
    )

    fun countryApi()

    fun stateApi(countryId : Int)
    fun cityApi(stateId : Int)
}