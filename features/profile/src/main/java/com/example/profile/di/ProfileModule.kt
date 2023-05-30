package com.example.profile.di

import com.example.core.util.Logger
import com.example.profile.navigation.ProfileNavigation
import com.example.profile.navigation.ProfileNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ProfileModule {

    @Singleton
    @Provides
    fun provideProfileNavigation() : ProfileNavigation {
        return ProfileNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("profileLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "Profile::",
            isDebug = true
        )
    }
}