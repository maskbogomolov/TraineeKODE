package com.example.appkode.data

import com.example.appkode.data.database.LocalDataSource
import com.example.appkode.data.database.toDomain
import com.example.appkode.data.network.RemoteDataSource

import com.example.appkode.data.network.toEntity
import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.NetworkResponse
import com.example.appkode.util.doOnSuccess
import com.example.appkode.util.mapResponse
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
}