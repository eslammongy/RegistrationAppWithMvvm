package com.eslammongy.registrationappwithmvvm.data.repository

import com.eslammongy.registrationappwithmvvm.data.UserPreferences
import com.eslammongy.registrationappwithmvvm.data.network.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.userLogin(email , password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }

}