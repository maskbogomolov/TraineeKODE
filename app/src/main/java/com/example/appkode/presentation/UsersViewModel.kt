package com.example.appkode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData

import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.NetworkResponse
import javax.inject.Inject
import javax.inject.Provider

class UsersViewModel(val repository: UsersRepository): ViewModel() {

    val users = liveData<NetworkResponse<List<User>>> {
        val data = repository.getUsers()
        emit(data)
    }

    class Factory @Inject constructor(val api: Provider<UsersRepository>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == UsersViewModel::class.java)
            return UsersViewModel(api.get()) as T
        }
    }
}