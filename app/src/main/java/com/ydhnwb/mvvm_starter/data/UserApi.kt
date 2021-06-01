package com.ydhnwb.mvvm_starter.data

import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun users() : Call<List<UserResponse>>
}