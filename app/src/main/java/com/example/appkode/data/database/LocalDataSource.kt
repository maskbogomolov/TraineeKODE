package com.example.appkode.data.database

import javax.inject.Inject

interface LocalDataSource {

    suspend fun insertAll(usersEntity: List<UsersEntity>)
}

class LocalDataSourceImpl @Inject constructor(private val dao: UsersDao) : LocalDataSource {

    override suspend fun insertAll(usersEntity: List<UsersEntity>) {
        dao.insertAll(usersEntity)
    }

}