package com.ydhnwb.mvvm_starter.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private var retrofit: Retrofit? = null
    private val client = OkHttpClient.Builder().apply {
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
    }.build()

    private fun provideRetrofit() : Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder().apply {
                client(client)
                baseUrl("https://jsonplaceholder.typicode.com/")
                addConverterFactory(GsonConverterFactory.create())
            }.build()
            return retrofit!!
        }
        return retrofit!!
    }

    fun provideUserApi() = provideRetrofit().create(UserApi::class.java)
    fun provideUserDetailApi() = provideRetrofit().create(UserDetailApi::class.java)

}