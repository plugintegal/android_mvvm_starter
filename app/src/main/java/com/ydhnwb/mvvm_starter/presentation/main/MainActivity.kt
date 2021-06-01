package com.ydhnwb.mvvm_starter.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import com.ydhnwb.mvvm_starter.databinding.ActivityMainBinding
import com.ydhnwb.mvvm_starter.presentation.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupRecyclerView()
        observe()
        fetchUserData()
    }

    private fun observe(){
        //in old version of kotlin, use viewModel.num.observe(this, Observe { ... })
        viewModel.users.observe(this, {  handleUsers(it )})
    }

    private fun fetchUserData(){
        viewModel.fetchUsers()
    }


    private fun handleUsers(users: List<UserResponse>){
        binding.userRecyclerview.adapter?.let { adapter ->
            if(adapter is MainUserAdapter){
                adapter.updateList(users)
            }
        }
    }

    private fun goToDetailActivity(user: UserResponse){
        startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra("id", user.id.toString())
        })
    }

    private fun setupRecyclerView(){
        val mainUserAdapter = MainUserAdapter(mutableListOf())
        mainUserAdapter.setItemTapListener(object : MainUserAdapter.OnItemTap{
            override fun onTap(user: UserResponse) = goToDetailActivity(user)
        })

        binding.userRecyclerview.apply {
            adapter = mainUserAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupViewModel(){
        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

}