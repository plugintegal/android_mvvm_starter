package com.ydhnwb.mvvm_starter.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydhnwb.mvvm_starter.data.UserDetailApi
import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val userDetailApi: UserDetailApi) : ViewModel() {
    companion object {
        private const val TAG = "DetailViewModel"
    }

    private val _user = MutableLiveData<UserResponse?>(null)
    //why this is LiveData but the _user is MutableLiveData?
    //because we dont want to expose the mutability to the view,
    //so we use LiveData for observing in view, since LiveData is immutable. for security reason
    val user : LiveData<UserResponse?> get() = _user

    fun fetchUserById(id: String){
        userDetailApi.getUserById(id).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    _user.value = response.body()
                }else{
                    Log.e(TAG, "Failed to get user detail")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}