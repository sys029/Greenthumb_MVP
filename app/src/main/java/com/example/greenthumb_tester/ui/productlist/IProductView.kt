package com.example.greenthumb_tester.ui.productlist

import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.model.productlist.ProductResponse

interface IProductView {

    fun onProductSuccess(loginBase: ProductResponse)
    fun onProductError(error: Error)
}