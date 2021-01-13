package com.example.greenthumb_tester

interface IProductView {

    fun onProductSuccess(loginBase:ProductResponse)
    fun onProductError(error: Error)
}