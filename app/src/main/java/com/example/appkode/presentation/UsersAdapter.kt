package com.example.appkode.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.appkode.data.UserDto
import com.example.appkode.databinding.UserItemBinding

class UsersAdapter(
    private val requireActivity : FragmentActivity,
    private val viewModel: UsersViewModel
): ListAdapter<UserDto, UsersViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(layoutInflater,parent,false)
        return UsersViewHolder(binding,requireActivity,viewModel)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class UsersDiffCallback : DiffUtil.ItemCallback<UserDto>() {

        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean =
            oldItem == newItem
    }
}