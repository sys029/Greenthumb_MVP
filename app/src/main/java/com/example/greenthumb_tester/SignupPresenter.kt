package com.example.greenthumb_tester

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPresenter(var iSignupView: ISignupView,var context: Context):ISignupPresenter  {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()

    override fun callSignUpAPI(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        userType: Int,
        emailId: String,
        country: String,
        city: String,
        state: String
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
                            iSignupView.onCountrySuccess(response)
                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iSignupView.onCountryError(res)
                        }

                    }

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(context, "Error Loading",Toast.LENGTH_SHORT).show()
                }

            })


    }

    override fun stateApi(countryId:String) {
        RetrofitClient.instance.stateList(countryId)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    when{

                        response.code() == 200 -> {
                            iSignupView.countrySpinnerSuccess(response)

                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                        }


                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(context, "Error Loading",Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun cityApi(stateId: String) {
        RetrofitClient.instance.cityList(stateId)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    when{

                        response.code() == 200 -> {
                            iSignupView.stateSpinnerSuccess(response)
                        }

                        response.code() == 400 -> {
                            val res = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                        }


                    }

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            })
    }


}