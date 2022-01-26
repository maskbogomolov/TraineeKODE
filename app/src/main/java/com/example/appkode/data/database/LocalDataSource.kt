package com.example.appkode.data.database

import com.example.appkode.util.SortOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {

    suspend fun insertAll(usersEntity: List<UsersEntity>)
    fun searchUsers(dep: String,query: String, sortOrder: SortOrder): Flow<List<UsersEntity>>
    suspend fun checkFilter(key : String) : Boolean
    suspend fun saveFilterMode(key: String,value : Boolean)
}

class LocalDataSourceImpl @Inject constructor(private val dao: UsersDao,private val sharedPreferences: SharedPreferences) : LocalDataSource {

    override suspend fun insertAll(usersEntity: List<UsersEntity>) {
        dao.insertAll(usersEntity)
    }

    override fun searchUsers(dep: String, query: String, sortOrder: SortOrder): Flow<List<UsersEntity>> {
        return dao.searchUsersWithFilter(dep, query, sortOrder)
    }

    override suspend fun checkFilter(key: String): Boolean {
        return sharedPreferences.isFilter(key)
    }

    override suspend fun saveFilterMode(key: String, value: Boolean) {
        sharedPreferences.saveFilterMode(key,value)
    }

}