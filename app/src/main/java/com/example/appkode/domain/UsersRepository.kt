package com.example.appkode.domain

import com.example.appkode.data.database.UsersEntity
import com.example.appkode.util.NetworkResponse
import com.example.appkode.util.SortOrder
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers(): NetworkResponse<List<User>>
    fun searchUsers(dep: String,query:String,sortOrder: SortOrder): Flow<List<User>>
    suspend fun checkFilter(key : String) : Boolean
    suspend fun saveFilterMode(key: String,value : Boolean)

}