package com.example.appkode.di


import com.example.appkode.data.network.UsersApi
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideUsersApi(): UsersApi{
        return UsersApi()
    }

}