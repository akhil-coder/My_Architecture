package com.example.my_list.di

import com.example.core.util.Logger
import com.example.my_list.navigation.MyListNavigation
import com.example.my_list.navigation.MyListNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MyListModule {

    @Singleton
    @Provides
    fun provideMyListNavigation() : MyListNavigation {
        return MyListNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("myListLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "MyList::",
            isDebug = true
        )
    }
}