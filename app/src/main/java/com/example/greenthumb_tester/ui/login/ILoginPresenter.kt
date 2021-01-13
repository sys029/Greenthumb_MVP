package com.example.greenthumb_tester.ui.login

interface ILoginPresenter {
    fun callLoginAPI(email: String, password: String, userType: Int, providerType: Int)

}