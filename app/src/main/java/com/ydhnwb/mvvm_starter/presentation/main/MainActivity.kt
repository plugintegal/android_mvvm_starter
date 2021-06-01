package com.ydhnwb.mvvm_starter.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ydhnwb.mvvm_starter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        observe()
        doDecrement()
        doIncrement()
    }

    private fun observe(){
        //in old version of kotlin, use viewModel.num.observe(this, Observe { ... })
        viewModel.num.observe(this, {  handleNum(it )})
    }

    private fun handleNum(num: Int){
        binding.numTextview.text = num.toString()
    }

    private fun setupViewModel(){
        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun doIncrement(){
        binding.incrementButton.setOnClickListener {
            viewModel.increment()
        }
    }

    private fun doDecrement(){
        binding.decrementButton.setOnClickListener {
            viewModel.decrement()
        }
    }
}