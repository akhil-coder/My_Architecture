package com.example.domain.service.network

import com.example.core.domain.DataState
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.model.movieList.Movie
import kotlinx.coroutines.flow.Flow

interface MovieNetworkService {
    suspend fun getMovies() : Flow<DataState<List<Movie>>>

    suspend fun getMovieDetails(movieId: String) : Flow<DataState<MovieDetails>>
}