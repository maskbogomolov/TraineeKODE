package com.example.appkode.di

import com.example.appkode.MainActivity
import com.example.appkode.presentation.UsersListFragment
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: UsersListFragment)
}
@Module(includes = [NetworkModule::class, BindsModule::class])
class AppModule