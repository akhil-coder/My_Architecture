package com.example.auth.di

import com.example.auth.navigation.AuthNavigation
import com.example.auth.navigation.AuthNavigationImpl
import com.example.core.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthNavigation() : AuthNavigation {
        return AuthNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("authLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "Auth::",
            isDebug = true
        )
    }
}