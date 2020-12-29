package com.example.greenthumb_tester

data class LoginResponse(val status: String?, val message:String?, val data: User)

data class Error(val status: String?,val message: String?)