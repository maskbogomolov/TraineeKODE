package com.example.appkode.data

import com.example.appkode.data.network.RemoteDataSource
import com.example.appkode.data.network.toDomain
import com.example.appkode.domain.User
import com.example.appkode.domain.UsersRepository
import com.example.appkode.util.NetworkResponse
import com.example.appkode.util.mapResponse
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource) : UsersRepository {

    override suspend fun getUsers(): NetworkResponse<List<User>> {
        return remoteDataSource.getUsers()
            .mapResponse { users -> users.map { it.toDomain() } }
    }
}