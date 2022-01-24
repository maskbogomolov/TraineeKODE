package com.example.appkode.data.network

import com.example.appkode.util.NetworkResponse
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getUsers(): NetworkResponse<List<UserDto>>
}
class RemoteDataSourceImpl @Inject constructor(private val api: UsersApi): RemoteDataSource{

    override suspend fun getUsers(): NetworkResponse<List<UserDto>> {
         try {
            val data = api.getUsers()
            return NetworkResponse.Success(data.items)
        }catch (e: Throwable){
            return NetworkResponse.Error("error")
        }
    }
}