package com.example.datasource.di

import com.example.datasource.cache.data.MovieDao
import com.example.datasource.cache.databaseService.FavoriteDbServiceImpl
import com.example.domain.service.cache.FavoriteDbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbServiceModule {

    @Singleton
    @Provides
    fun provideFavoriteDbService(movieDao: MovieDao): FavoriteDbService = FavoriteDbServiceImpl(movieDao)
}