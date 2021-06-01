package com.ydhnwb.mvvm_starter.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ydhnwb.mvvm_starter.data.NetworkModule

//we separate this ViewModelFactory because the parameters to pass is different
@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory : ViewModelProvider.Factory {
    private val userDetailApi = NetworkModule.provideUserDetailApi()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(userDetailApi) as T
            }
            else -> throw Throwable("Unknown ViewModelType")
        }
    }
}