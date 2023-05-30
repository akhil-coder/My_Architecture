package com.example.tvShow.di

import com.example.core.util.Logger
import com.example.tvShow.navigation.TvShowScreenNavigation
import com.example.tvShow.navigation.TvShowScreenNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TvShowModule {

    @Singleton
    @Provides
    fun provideTvShowScreenNavigation(): TvShowScreenNavigation {
        return TvShowScreenNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("TvShowScreenLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "Movie::",
            isDebug = true
        )
    }
}