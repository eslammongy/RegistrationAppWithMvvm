package com.eslammongy.registrationappwithmvvm.data.repository


import com.eslammongy.registrationappwithmvvm.data.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }

}