package com.example.appkode.data

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