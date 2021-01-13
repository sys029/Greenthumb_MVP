package com.example.greenthumb_tester.ui.registation

import android.content.Context
import android.widget.Toast
import com.example.greenthumb_tester.R
import com.example.greenthumb_tester.model.registration.SignupResponse
import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.network.RetrofitClient
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPresenter(var iSignupView: ISignupView, var context: Context):
    ISignupPresenter {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()

    override fun callSignUpAPI(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        userType: Int,
        emailId: String,
        country: Int,
        city: Int,
        state: Int
    ) {
        RetrofitClient.instance.userSignup(firstName, lastName,emailId,password,phone,country,state,city,userType)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {


                    when {
                        response.code() == 400 -> {
                            val loginBase = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iSignupView.onSignupError(loginBase)
                        }
                        response.code() == 200 -> {
                            val loginBase = gson.fromJson(response.body().toString(), SignupResponse::class.java)
                            Toast.makeText(context, loginBase.message, Toast.LENGTH_LONG).show()
                            iSignupView.onSignupSuccess(loginBase)
                        }
                        else -> {
                            Toast.makeText(context, context.resources.getString(R.string.something_went),Toast.LENGTH_SHORT).show()
                        }
                    }
                }  override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            })


    }

    override fun countryApi() {
        RetrofitClient.instance.countryList()
            .enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    when{
                        response.code() == 200 -> {
                            iSignupView.countrySpinner(response)
                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iSignupView.onSignupError(res)
                        }

                    }

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(context, "Error Loading",Toast.LENGTH_SHORT).show()
                }

            })


    }

    override fun stateApi(countryId:Int) {
        RetrofitClient.instance.stateList(countryId)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    when{

                        response.code() == 200 -> {
                            iSignupView.stateSpinner(response)

                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iSignupView.onSignupError(res)
                        }


                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(context, "Error Loading",Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun cityApi(stateId: Int) {
        RetrofitClient.instance.cityList(stateId)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    when{

                        response.code() == 200 -> {
                            iSignupView.citySpinner(response)
                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iSignupView.onSignupError(res)
                        }


                    }

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            })
    }


}