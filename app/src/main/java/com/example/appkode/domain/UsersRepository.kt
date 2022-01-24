package com.example.appkode.domain

import com.example.appkode.util.NetworkResponse

interface UsersRepository {

    suspend fun getUsers(): NetworkResponse<List<User>>
}