package com.example.appkode.data

import com.example.appkode.data.database.LocalDataSource
import com.example.appkode.data.database.UsersEntity
import com.example.appkode.data.database.toDomain
import com.example.appkode.data.network.RemoteDataSource

import com.example.appkode.data.network.toEntity
import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : UsersRepository {

    override suspend fun getUsers(): NetworkResponse<List<User>> {
        return remoteDataSource.getUsers()
            .mapResponse { usersDto -> usersDto.map { it.toEntity() } }
            .doOnSuccess { entities -> localDataSource.insertAll(entities) }
            .mapResponse { entities -> entities.map { it.toDomain() } }
    }


    override fun searchUsers(dep: String,query: String,sortOrder: SortOrder ): Flow<List<User>> {
       return localDataSource.searchUsers(dep, query, sortOrder)
           .mapToUserDomain { usersEntity -> usersEntity.toDomain() }
    }

    override suspend fun checkFilter(key: String): Boolean {
        return localDataSource.checkFilter(key)
    }

    override suspend fun saveFilterMode(key: String, value: Boolean) {
        localDataSource.saveFilterMode(key, value)
    }



}