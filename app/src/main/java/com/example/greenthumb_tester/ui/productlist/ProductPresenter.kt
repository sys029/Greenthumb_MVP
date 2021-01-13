package com.example.greenthumb_tester.ui.productlist

import android.content.Context
import android.widget.Toast
import com.example.greenthumb_tester.R
import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.model.productlist.ProductResponse
import com.example.greenthumb_tester.network.RetrofitClient
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(var iProductView: IProductView, var context: Context):
    IProductPresenter {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()

    override fun callProductApi() {
        RetrofitClient.instance.productList()
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {


                    when {
                        response.code() == 400 -> {
                            val loginBase = gson.fromJson(response.errorBody()?.charStream(), Error::class.java)
                            iProductView.onProductError(loginBase)
                        }
                        response.code() == 200 -> {
                            val loginBase = gson.fromJson(response.body().toString(), ProductResponse::class.java)
                            Toast.makeText(context, loginBase.message, Toast.LENGTH_LONG).show()
                            iProductView.onProductSuccess(loginBase)

                        }
                        else -> {
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.something_went),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }  override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            })
    }
}