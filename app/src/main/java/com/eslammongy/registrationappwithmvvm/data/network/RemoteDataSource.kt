package com.eslammongy.registrationappwithmvvm.data.network

import com.eslammongy.registrationappwithmvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    companion object{
        private const val Base_URl = "http://simplifiedcoding.tech/mywebapp/public/api/"
    }

    fun <Api> buildAPi(api: Class<Api>):Api{

        return  Retrofit.Builder()
            .baseUrl(Base_URl)
            .client(OkHttpClient.Builder().also { okHttpClient ->
                if (BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
                    okHttpClient.readTimeout(10, TimeUnit.SECONDS)
                    okHttpClient.writeTimeout(10, TimeUnit.SECONDS)
                    okHttpClient.addInterceptor(logging)
                }
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}