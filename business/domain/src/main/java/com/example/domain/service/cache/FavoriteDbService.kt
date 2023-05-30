package com.example.domain.service.cache

import com.example.domain.model.movieDetails.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoriteDbService {

    fun getFavoriteMovies(): Flow<List<MovieDetails>>

    suspend fun getFavoriteById(id: Int): MovieDetails?

    suspend fun addFavorite(movie: MovieDetails)

    suspend fun updateFavorite(movie: MovieDetails)

    suspend fun deleteFavorite(movie: MovieDetails)
}