package com.example.appkode.di

import android.content.Context
import com.example.appkode.MainActivity
import com.example.appkode.presentation.UsersListFragment
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class],dependencies = [AppDeps::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: UsersListFragment)

    @Component.Builder
    interface Builder{
        fun appDeps(appDeps: AppDeps): Builder
        fun bild(): AppComponent
    }
}
interface AppDeps{

    val context: Context
}
@Module(includes = [NetworkModule::class, BindsModule::class,DatabaseModule::class])
class AppModule