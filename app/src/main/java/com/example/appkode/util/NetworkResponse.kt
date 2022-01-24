package com.example.appkode.util

sealed class NetworkResponse<out T>{

    data class Success<T>(val data: T): NetworkResponse<T>()
    data class Error <T>(val message: String): NetworkResponse<T>()
}
