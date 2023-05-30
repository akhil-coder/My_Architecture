package com.example.domain.service.network

import com.example.core.domain.DataState
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.model.movieList.Movie
import com.example.domain.model.tvList.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowNetworkService {
    suspend fun discoverTvShows() : Flow<DataState<List<TvShow>>>
}