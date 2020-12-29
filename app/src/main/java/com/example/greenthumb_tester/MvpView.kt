package com.example.greenthumb_tester

import android.widget.ProgressBar

interface MvpView {

    fun setProgressBar(bar: ProgressBar )
    fun showProgressBar()
    fun hideProgressBar()

    fun showConnected(isConnected:Boolean)
    fun showMessage(message:String)

}