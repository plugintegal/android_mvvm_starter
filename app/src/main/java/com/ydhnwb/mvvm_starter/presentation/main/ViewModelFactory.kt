package com.ydhnwb.mvvm_starter.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ydhnwb.mvvm_starter.data.NetworkModule

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    private val userApi = NetworkModule.provideUserApi()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(userApi) as T
            }
            else -> throw Throwable("Unknown ViewModel type")
        }
    }
}