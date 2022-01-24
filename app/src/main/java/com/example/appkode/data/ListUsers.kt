package com.example.appkode.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListUsers(
    @SerializedName("items")
    val items: List<UserDto>
)