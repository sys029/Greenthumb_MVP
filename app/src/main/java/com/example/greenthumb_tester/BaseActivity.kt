package com.example.greenthumb_tester

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity(),ConnectivityReceiver.ConnectivityReceiverListener,MvpView {


    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }


    override fun showConnected(isConnected: Boolean) {
        if (!isConnected) {

            mSnackBar = Snackbar.make(findViewById(R.id.rootLayout), "You are offline now.", Snackbar.LENGTH_LONG)
            mSnackBar?.show()
        } else {

            mSnackBar = Snackbar.make(findViewById(R.id.rootLayout), "You are online now.", Snackbar.LENGTH_LONG)
            mSnackBar?.show()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showConnected(isConnected)
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }


    //ProgressBar
    private var progressBar: ProgressBar? = null

    override fun setProgressBar(bar: ProgressBar) {
        progressBar = bar
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }




    public override fun onStop() {
        super.onStop()
        hideProgressBar()
    }
}