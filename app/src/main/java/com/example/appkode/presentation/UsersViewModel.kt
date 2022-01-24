package com.example.appkode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.appkode.data.UserDto
import com.example.appkode.data.UsersApi
import javax.inject.Inject
import javax.inject.Provider

class UsersViewModel(val api: UsersApi): ViewModel() {

    val users = liveData<List<UserDto>> {
        val data = api.getUsers()
        emit(data.items)
    }

    class Factory @Inject constructor(val api: Provider<UsersApi>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == UsersViewModel::class.java)
            return UsersViewModel(api.get()) as T
        }
    }
}