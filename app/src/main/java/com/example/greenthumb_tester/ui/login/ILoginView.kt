package com.example.greenthumb_tester.ui.login

import com.example.greenthumb_tester.ui.splashscreen.MvpView
import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.model.login.LoginResponse

interface ILoginView: MvpView {

    fun onSuccess(loginBase: LoginResponse)
    fun onError(error: Error)

}