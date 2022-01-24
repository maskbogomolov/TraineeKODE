package com.example.appkode.presentation

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appkode.data.database.UsersEntity
import com.example.appkode.data.network.UserDto
import com.example.appkode.databinding.UserItemBinding
import com.example.appkode.domain.User

class UsersViewHolder(
    private val binding: UserItemBinding,
    private val requireActivity : FragmentActivity,
    private val viewModel: UsersViewModel
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: User){
        setName(data)
        setDepartment(data)
        setTag(data)
        loadImage(data)
    }
    fun setName(data: User){
        binding.nameTxt.text = "${data.lastName} ${data.firstName}"
    }
    fun setDepartment(data: User){
        binding.departmentTxt.text = data.department
    }
    fun setTag(data: User){
        binding.tagTxt.text = data.userTag
    }
    fun loadImage(data: User){
        binding.imageView.load(data.avatarUrl)
    }
}