package com.ydhnwb.mvvm_starter.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)