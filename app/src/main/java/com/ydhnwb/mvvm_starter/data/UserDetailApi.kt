package com.ydhnwb.mvvm_starter.data

import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailApi {
    @GET("users/{id}")
    fun getUserById(@Path("id") id: String) : Call<UserResponse>
}