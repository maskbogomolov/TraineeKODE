package com.example.appkode.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.appkode.data.database.UsersEntity

import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.Data
import com.example.appkode.util.NetworkResponse
import com.example.appkode.util.SortOrder
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class UsersViewModel(val repository: UsersRepository): ViewModel() {

    val sortOrder = MutableStateFlow(SortOrder.NONE)
    val queryFlow = MutableStateFlow("")
    val department = MutableStateFlow("")
    private val _searchUsers = MutableStateFlow<Data>(Data.EmptyQuery)

    private val usersFlow = combine(
        department,queryFlow
    ){ department,query ->
        Pair(department,query)
    }.flatMapLatest {(department,query) -> repository.searchUsers(department,query)}

    val filter = usersFlow.asLiveData()

    fun getUserFromDb() : LiveData<List<User>>{
        return repository.getUsersByDep(department.value).asLiveData()
    }
    fun getUser(){
        viewModelScope.launch {
            repository.getUsers()
        }
    }

    class Factory @Inject constructor(val api: Provider<UsersRepository>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == UsersViewModel::class.java)
            return UsersViewModel(api.get()) as T
        }
    }
}