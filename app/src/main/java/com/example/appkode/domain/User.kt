package com.example.appkode.domain

import com.google.gson.annotations.SerializedName

data class User(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)
