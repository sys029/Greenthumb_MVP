package com.example.greenthumb_tester

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(var iLoginView: ILoginView,var context: Context):ILoginPresenter {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()

    override fun callLoginAPI(email: String, password: String, userType: Int, providerType: Int) {

        iLoginView.showProgressBar();

            RetrofitClient.instance.userLogin(email,password,1,1).
            enqueue(object: Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    when {
                        response.code() == 400 -> {
                            iLoginView.hideProgressBar();
                            val loginBase = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iLoginView.onError(loginBase)
                        }
                        response.code() == 200 -> {
                            iLoginView.hideProgressBar();
                            val loginBase = gson.fromJson(response.body().toString(), LoginResponse::class.java)
                            iLoginView.onSuccess(loginBase)


                        }
                        else -> {
                            iLoginView.hideProgressBar();
                            iLoginView.showMessage(context.resources.getString(R.string.something_went))
                        }


                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            })


    }


}