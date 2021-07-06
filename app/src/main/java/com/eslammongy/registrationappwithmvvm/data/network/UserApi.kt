package com.eslammongy.registrationappwithmvvm.data.network

import com.eslammongy.registrationappwithmvvm.data.responce.LoginResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("user")
    suspend fun getUser(): LoginResponse
}