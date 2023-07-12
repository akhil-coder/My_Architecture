package com.example.interactors.di

import com.example.datasource.network.WebService
import com.example.domain.service.cache.FavoriteDbService
import com.example.domain.service.network.HomeUiNetworkService
import com.example.domain.service.network.MovieNetworkService
import com.example.interactors.favorite.*
import com.example.interactors.homeUi.GetHomeUi
import com.example.interactors.homeUi.HomeUiInteractor
import com.example.interactors.movies.GetAllMovies
import com.example.interactors.movies.GetMovieDetails
import com.example.interactors.movies.MovieInteractor
import com.example.interactors.tvShow.TvShowInteractor
import com.example.interactors.tvShow.TvShowPager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object InteractorsModule {

    @Provides
    @Singleton
    fun provideHomeUiInteractor(service: HomeUiNetworkService) = HomeUiInteractor(
        getHomeUi = GetHomeUi(networkService = service)
    )

    @Provides
    @Singleton
    fun provideMovieInteractor(service: MovieNetworkService) = MovieInteractor(
        getAllMovies = GetAllMovies(networkService = service),
        getMovieDetails = GetMovieDetails(networkService = service)
    )

    @Provides
    @Singleton
    fun provideTvShowInteractor(service: WebService) = TvShowInteractor(
        discoverTvShow = TvShowPager(service)
    )

    @Provides
    @Singleton
    fun provideTvShowPager(service: WebService) = TvShowPager(
        service = service
    )

    @Provides
    @Singleton
    fun provideFavoriteInteractor(service: FavoriteDbService) = FavoriteInteractor(
        getFavorites = GetFavorites(favoriteDbService = service),
        addFavorite = AddFavorite(favoriteDbService = service),
        getFavoriteById = GetFavoriteById(favoriteDbService = service),
        removeFavorite = RemoveFavorite(favoriteDbService = service)
    )
}