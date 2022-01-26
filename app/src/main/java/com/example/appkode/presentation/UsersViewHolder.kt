package com.example.appkode.presentation

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
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
        itemView.setOnClickListener {
            navigateToDetails(data,it)
        }

    }

    fun navigateToDetails(data: User,view:View) {
        val direction = HomeViewPagerFragmentDirections
            .actionHomeViewPagerFragmentToDetailsFragment(data)
        view.findNavController().navigate(direction)
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