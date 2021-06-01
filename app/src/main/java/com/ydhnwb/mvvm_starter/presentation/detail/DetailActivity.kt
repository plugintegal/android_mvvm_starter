package com.ydhnwb.mvvm_starter.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import com.ydhnwb.mvvm_starter.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        fetchUserDetail()
        observe()
    }

    private fun observeUser(){
        //use viewLifeCycleOwner if in fragment
        viewModel.user.observe(this, { handleUser(it) })
    }

    private fun observeState(){
        viewModel.state.observe(this, { handleState(it) })
    }

    private fun handleState(state: DetailActivityState){
        when(state){
            is DetailActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleLoading(isShouldShowProgressBar: Boolean){
        binding.loadingProgressBar.isIndeterminate = isShouldShowProgressBar
        if(!isShouldShowProgressBar){
            binding.loadingProgressBar.isIndeterminate = false
            binding.loadingProgressBar.progress = 0
        }
    }

    private fun observe(){
        observeState()
        observeUser()
    }

    private fun handleUser(user: UserResponse?){
        user?.let {
            binding.userTextView.text = it.name
        }
    }

    private fun getPassedId() = intent.getStringExtra("id")!!

    private fun setupViewModel(){
        //use viewLifeCycleOwner is you create in a fragment, instead of this@xxActivity
        viewModel = ViewModelProvider(this, DetailViewModelFactory()).get(DetailViewModel::class.java)
    }

    private fun fetchUserDetail() = viewModel.fetchUserById(getPassedId())
}