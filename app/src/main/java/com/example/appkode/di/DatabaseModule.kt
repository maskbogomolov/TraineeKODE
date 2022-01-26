package com.example.appkode.di

import android.content.Context
import com.example.appkode.data.database.AppDatabase
import com.example.appkode.data.database.SharedPreferences
import com.example.appkode.util.Const.PREF_NAME
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

    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences{
        return SharedPreferences(context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE))
    }
}