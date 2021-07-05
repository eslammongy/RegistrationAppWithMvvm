package com.eslammongy.registrationappwithmvvm.data.repository

import com.eslammongy.registrationappwithmvvm.data.network.AuthApi

class AuthRepository (private val api:AuthApi): BaseRepository() {

    suspend fun login(email:String , password:String) = safeApiCall {
        api.userLogin(email , password)
    }
}