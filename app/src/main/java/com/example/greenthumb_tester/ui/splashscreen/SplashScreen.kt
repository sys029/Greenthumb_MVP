package com.example.greenthumb_tester.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.greenthumb_tester.ui.home.HomeActivity
import com.example.greenthumb_tester.ui.login.MainActivity
import com.example.greenthumb_tester.util.SharedPrefManager

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }else{
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }


    }
    

}
