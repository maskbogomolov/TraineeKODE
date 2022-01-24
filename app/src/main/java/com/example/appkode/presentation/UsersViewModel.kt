package com.example.appkode.presentation

import androidx.lifecycle.*
import com.example.appkode.data.database.UsersEntity

import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.Data
import com.example.appkode.util.NetworkResponse
import com.example.appkode.util.SortOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class UsersViewModel(val repository: UsersRepository): ViewModel() {

    val sortOrder = MutableStateFlow(SortOrder.NONE)
    val queryFlow = MutableStateFlow("")
    val department = MutableStateFlow("")
    val _searchData = MutableStateFlow(Data.EmptyQuery)
    //val searchData: LiveData<Data> get() = _searchData(c)

//    val users = liveData<NetworkResponse<List<User>>> {
//        val data = repository.getUsers()
//        emit(data)
//    }

    val _listUsers = repository.getUsersByDep(department.value).asLiveData()
        val listUsers get() = _listUsers
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