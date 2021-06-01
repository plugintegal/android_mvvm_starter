package com.ydhnwb.mvvm_starter.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ydhnwb.mvvm_starter.data.dto.UserResponse
import com.ydhnwb.mvvm_starter.databinding.ItemUserBinding

class MainUserAdapter(private val users: MutableList<UserResponse>) : RecyclerView.Adapter<MainUserAdapter.ViewHolder>() {
    interface OnItemTap {
        fun onTap(user: UserResponse)
    }

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    private var onTapListener: OnItemTap? = null

    fun updateList(list: List<UserResponse>){
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val itemBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(user: UserResponse){
            itemBinding.nameTextView.text = user.name
            itemBinding.root.setOnClickListener {
                onTapListener?.onTap(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount() = users.size
}