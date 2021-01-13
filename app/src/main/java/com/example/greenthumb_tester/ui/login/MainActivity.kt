package com.example.greenthumb_tester.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.greenthumb_tester.ui.splashscreen.BaseActivity
import com.example.greenthumb_tester.ui.home.HomeActivity
import com.example.greenthumb_tester.R
import com.example.greenthumb_tester.ui.registation.SignUpActivity
import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.model.login.LoginResponse
import com.example.greenthumb_tester.util.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    ILoginView {


    private val loginPresenter=
        LoginPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProgressBar(progressBar)
        progressBar.visibility = View.GONE


        buttonLogin.setOnClickListener {

            showProgressBar()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if(email.isEmpty()){
                hideProgressBar()
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                hideProgressBar()
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            val user_type: Int = 1
            val provider_type: Int = 1


            loginPresenter.callLoginAPI(email,password,user_type,provider_type)

        }

        signupText.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)

        }


    }



    override fun onSuccess(loginBase: LoginResponse) {
        showMessage(loginBase.message!!)
        if( loginBase.status == "success"){
            SharedPrefManager.getInstance(applicationContext).saveUser(loginBase.data!!)
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }else{
            showMessage(loginBase.message!!)
        }



    }

    override fun onError(error: Error) {
        showMessage(error.message!!)
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }


}
