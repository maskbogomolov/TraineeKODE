package com.example.appkode.di

import android.content.Context
import com.example.appkode.data.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }
    @Provides
    fun provideDao(database: AppDatabase) = database.usersDao()
}