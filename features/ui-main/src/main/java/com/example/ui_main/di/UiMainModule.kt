package com.example.ui_main.di

import com.example.core.util.Logger
import com.example.ui_main.navigation.UiMainNavigation
import com.example.ui_main.navigation.UiMainNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UiMainModule {

    @Singleton
    @Provides
    fun provideUiMainNavigation() : UiMainNavigation {
        return UiMainNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("mainLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "Main_UI::",
            isDebug = true
        )
    }
}