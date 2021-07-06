package com.eslammongy.registrationappwithmvvm.data.repository

import com.eslammongy.registrationappwithmvvm.data.network.BaseApi
import com.eslammongy.registrationappwithmvvm.data.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() =  safeApiCall{
        api.logout()
    }
}