package com.example.greenthumb_tester.ui.splashscreen

import android.widget.ProgressBar

interface MvpView {

    fun setProgressBar(bar: ProgressBar )
    fun showProgressBar()
    fun hideProgressBar()

    fun showConnected(isConnected:Boolean)
    fun showMessage(message:String)

}