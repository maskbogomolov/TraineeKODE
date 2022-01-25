package com.example.appkode.util

import com.example.appkode.data.database.UsersEntity
import com.example.appkode.domain.User

sealed class Data{

    object EmptyQuery : Data()
    object EmptyResponse: Data()
    data class Success(val data: List<User>): Data()

}
