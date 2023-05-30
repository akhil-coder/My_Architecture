package com.example.movie.di

import com.example.core.util.Logger
import com.example.movie.navigation.MovieNavigation
import com.example.movie.navigation.MovieNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MovieModule {

    @Singleton
    @Provides
    fun provideMovieNavigation() : MovieNavigation {
        return MovieNavigationImpl()
    }

    @Singleton
    @Provides
    @Named("movieLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "Movie::",
            isDebug = true
        )
    }

}