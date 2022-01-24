package com.example.appkode.data.network

import com.example.appkode.data.database.UsersEntity
import com.example.appkode.domain.User
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("userTag")
    val userTag: String
)

fun UserDto.toEntity(): UsersEntity {
    return UsersEntity(
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
