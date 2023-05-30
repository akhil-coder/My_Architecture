package com.example.datasource.di

import com.example.datasource.network.WebService
import com.example.datasource.network.networkService.MovieNetworkServiceImpl
import com.example.datasource.network.networkService.TvShowNetworkServiceImpl
import com.example.domain.service.network.MovieNetworkService
import com.example.domain.service.network.TvShowNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkServiceModule {

    @Singleton
    @Provides
    fun provideMovieNetworkService(webService: WebService): MovieNetworkService =
        MovieNetworkServiceImpl(webService)


    @Singleton
    @Provides
    fun provideTvNetworkService(webService: WebService): TvShowNetworkService = TvShowNetworkServiceImpl(webService)
}