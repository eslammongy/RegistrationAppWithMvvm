package com.eslammongy.registrationappwithmvvm.data.network

import com.eslammongy.registrationappwithmvvm.data.responce.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi:BaseApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}