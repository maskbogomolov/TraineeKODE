package com.example.appkode.data.network

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListUsers(
    @SerializedName("items")
    val items: List<UserDto>
)