package com.example.appkode.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appkode.data.network.UserDto
import com.example.appkode.domain.User

@Entity(tableName = "users_table")
data class UsersEntity(
    @PrimaryKey
    val id: String,
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)
fun UsersEntity.toDomain(): User {
    return User(
        avatarUrl = avatarUrl,
        birthday = birthday,
        department = department,
        firstName = firstName,
        id = id,
        lastName = lastName,
        phone = phone,
        position = position,
        userTag = userTag
    )
}