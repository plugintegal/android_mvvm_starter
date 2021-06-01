package com.ydhnwb.mvvm_starter.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydhnwb.mvvm_starter.data.UserApi
import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val userApi: UserApi) : ViewModel(){
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _users = MutableLiveData<List<UserResponse>>(mutableListOf())
    val users : LiveData<List<UserResponse>> get() = _users

    fun fetchUsers(){
        userApi.users().enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful){
                    //postValue will set the value in background thread
                    //if you are dealing with ui state, please use .value instead
                    _users.postValue(response.body())
                }else{
                    Log.e(TAG, "Failed get data")
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}