package com.example.appkode.util

import com.example.appkode.data.database.UsersEntity

sealed class Data{

    object EmptyQuery : Data()
    object EmptyResponse: Data()
    data class Success(val data: List<UsersEntity>): Data()

}
