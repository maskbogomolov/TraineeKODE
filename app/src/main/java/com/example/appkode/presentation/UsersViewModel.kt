package com.example.appkode.presentation

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.lifecycle.*
import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class UsersViewModel(val repository: UsersRepository) : ViewModel() {

    val refreshUsers = MutableLiveData<NetworkResponse<List<User>>>()
    val sortOrder = MutableStateFlow(SortOrder.BY_NAME)
    val queryFlow = MutableStateFlow("")
    val department = MutableStateFlow("")

    private val usersFlow = combine(
        department, queryFlow , sortOrder
    ) { department, query , sortOrder->
        Triple(department, query, sortOrder)
    }.flatMapLatest { (department, query, sortOrder) -> repository.searchUsers(department, query, sortOrder) }

    val filter = usersFlow.asLiveData()

    suspend fun getUser() {
       return when(val refresh = repository.getUsers()){
           is NetworkResponse.Success -> refreshUsers.value = NetworkResponse.Success(refresh.data)
           is NetworkResponse.Error -> refreshUsers.value = NetworkResponse.Error("error")
       }
    }

    fun isOnline(appCon: Context): Boolean {
        val connMgr = appCon.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    suspend fun checkFilter(key : String) : Boolean{
        return repository.checkFilter(key)
    }
    fun saveFilterMode(key: String,value : Boolean) {
        viewModelScope.launch{
            repository.saveFilterMode(key, value)
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