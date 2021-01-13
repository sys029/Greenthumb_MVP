package com.example.greenthumb_tester.model.registration

import com.example.greenthumb_tester.model.login.User

data class SignupResponse(val status: String, val message:String, val data: User)