package com.example.greenthumb_tester

interface ILoginPresenter {
    fun callLoginAPI(email: String, password: String, userType: Int, providerType: Int)

}