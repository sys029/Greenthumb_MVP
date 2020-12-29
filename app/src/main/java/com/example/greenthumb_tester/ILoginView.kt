package com.example.greenthumb_tester

interface ILoginView:MvpView {

    fun onSuccess(loginBase:LoginResponse)
    fun onError(error: Error)

}