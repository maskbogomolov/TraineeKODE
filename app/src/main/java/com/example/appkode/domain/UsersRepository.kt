package com.example.appkode.domain

import com.example.appkode.data.database.UsersEntity
import com.example.appkode.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers(): NetworkResponse<List<User>>
    fun getUsersByDep(dep: String): Flow<List<User>>
    fun searchUsers(dep: String,query:String,): Flow<List<User>>
}