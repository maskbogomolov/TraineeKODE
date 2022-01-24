package com.example.appkode.di

import com.example.appkode.data.UsersRepositoryImpl
import com.example.appkode.data.database.LocalDataSource
import com.example.appkode.data.database.LocalDataSourceImpl
import com.example.appkode.data.network.RemoteDataSource
import com.example.appkode.data.network.RemoteDataSourceImpl
import com.example.appkode.domain.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface BindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bindRemoteDataSourceImpl_to_RemoteDataSource(
        impl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Suppress("FunctionName")
    @Binds
    fun bindRepositoryImpl_to_Repository(
        impl: UsersRepositoryImpl
    ): UsersRepository

    @Suppress("FunctionName")
    @Binds
    fun bindLocalDataSourceImpl_to_LocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource
}