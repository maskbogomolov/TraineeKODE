package com.example.appkode.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {

    suspend fun insertAll(usersEntity: List<UsersEntity>)
    fun getUsersByDep(dep: String): Flow<List<UsersEntity>>
    fun searchUsers(dep: String,query: String): Flow<List<UsersEntity>>
}

class LocalDataSourceImpl @Inject constructor(private val dao: UsersDao) : LocalDataSource {

    override suspend fun insertAll(usersEntity: List<UsersEntity>) {
        dao.insertAll(usersEntity)
    }

    override fun getUsersByDep(dep: String): Flow<List<UsersEntity>> {
        return dao.sortByDepartment(dep)
    }

    override fun searchUsers(dep: String, query: String): Flow<List<UsersEntity>> {
        return dao.searchUsers(dep, query)
    }

}