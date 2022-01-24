package com.example.appkode.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    suspend fun getUsers(): ListUsers
}

fun UsersApi(): UsersApi {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val json = Json {
        ignoreUnknownKeys = true
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("https://stoplight.io/mocks/kode-education/trainee-test/25143926/")
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
        .build()

    return retrofit.create(UsersApi::class.java)
}