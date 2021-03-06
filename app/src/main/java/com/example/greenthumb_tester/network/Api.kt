package com.example.greenthumb_tester.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email_id") email_id:String,
        @Field("password") password: String,
        @Field("user_type") user_type: Int,
        @Field("provider_type") provider_type: Int
    ): Call<JsonObject>


    @FormUrlEncoded
    @POST("registration")
    fun userSignup(
        @Field("first_name") first_name:String,
        @Field("last_name") last_name: String,
        @Field("email_id") email_id:String,
        @Field("password") password: String,
        @Field("user_phone1") user_phone1: String,
        @Field("country") country: Int,
        @Field("state") state: Int,
        @Field("city") city: Int,
        @Field("user_type") user_type: Int
    ): Call<JsonObject>

    @POST("country")
    fun countryList(): Call<JsonObject>

    @FormUrlEncoded
    @POST("state")
    fun stateList(
        @Field("country_id") countryIDAPI:Int
    ) : Call<JsonObject>

    @FormUrlEncoded
    @POST("city")
    fun cityList(
        @Field("state_id") stateIDAPI:Int
    ) : Call<JsonObject>

    @GET("productlist")
    fun productList(): Call<JsonObject>


}