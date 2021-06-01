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

    private val _state = MutableLiveData<MainActivityState>()
    val state : LiveData<MainActivityState> get() = _state

    private fun setLoading(){
        _state.value = MainActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = MainActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        _state.value = MainActivityState.ShowToast(message)
    }

    fun fetchUsers(){
        setLoading()
        userApi.users().enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                hideLoading()
                if (response.isSuccessful){
                    _users.postValue(response.body())
                }else{
                    showToast("Failed to get data")
                    Log.e(TAG, "Failed get data")
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                hideLoading()
                showToast(t.message.toString())
                Log.e(TAG, t.message.toString())
            }
        })
    }
}

sealed class MainActivityState {
    data class IsLoading(val isLoading: Boolean) : MainActivityState()
    data class ShowToast(val message: String) : MainActivityState()
}