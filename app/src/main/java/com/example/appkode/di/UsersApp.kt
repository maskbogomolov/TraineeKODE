package com.example.appkode.di

import android.app.Application
import android.content.Context
import com.example.appkode.di.AppComponent
import com.example.appkode.di.DaggerAppComponent

class UsersApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is UsersApp -> appComponent
        else -> applicationContext.appComponent
    }