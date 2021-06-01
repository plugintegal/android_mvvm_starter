package com.ydhnwb.mvvm_starter.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val _num = MutableLiveData(0)
    val num : LiveData<Int> get() = _num


    fun increment(){
        val current = _num.value!!
        _num.value = current + 1
    }

    fun decrement(){
        val current = _num.value!!
        _num.value = current - 1
    }
}