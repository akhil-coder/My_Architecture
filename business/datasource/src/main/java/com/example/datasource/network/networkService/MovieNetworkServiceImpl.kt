package com.example.datasource.network.networkService

import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.datasource.network.WebService
import com.example.datasource.network.mapper.toDomainMovieDetails
import com.example.datasource.network.mapper.toDomainMovieList
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.model.movieList.Movie
import com.example.domain.service.network.MovieNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieNetworkServiceImpl @Inject constructor(
    private val webService: WebService
) : MovieNetworkService {

    override suspend fun getMovies(): Flow<DataState<List<Movie>>> = flow {
        try {
            val response = webService.getMovies()
            if(response.isSuccessful) {
                emit(DataState.Data(response.body()!!.toDomainMovieList()))
            } else {
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Failed",
                            description = "Could not connect with server. Try again later"
                        )
                    )
                )
            }

        } catch (e: Exception) {
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<DataState<MovieDetails>> = flow{
        try {
            val response = webService.getMovieDetails(movieId)
            if(response.isSuccessful) {
                emit(DataState.Data(response.body()!!.toDomainMovieDetails()))
            } else {
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Failed",
                            description = "Could not connect with server. Try again later"
                        )
                    )
                )
            }

        } catch (e: Exception) {
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }
}