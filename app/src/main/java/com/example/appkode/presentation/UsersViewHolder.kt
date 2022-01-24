package com.example.appkode.presentation

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appkode.data.UserDto
import com.example.appkode.databinding.UserItemBinding

class UsersViewHolder(
    private val binding: UserItemBinding,
    private val requireActivity : FragmentActivity,
    private val viewModel: UsersViewModel
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UserDto){
        setName(data)
        setDepartment(data)
        setTag(data)
        loadImage(data)
    }
    fun setName(data: UserDto){
        binding.nameTxt.text = data.firstName + data.lastName
    }
    fun setDepartment(data: UserDto){
        binding.departmentTxt.text = data.department
    }
    fun setTag(data: UserDto){
        binding.tagTxt.text = data.userTag
    }
    fun loadImage(data: UserDto){
        binding.imageView.load(data.avatarUrl)
    }
}