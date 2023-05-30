package com.example.interactors.movies

import com.example.core.domain.DataState
import com.example.core.domain.ProgressBarState
import com.example.core.domain.UIComponent
import com.example.core.util.exhaustive
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.network.MovieNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetails constructor(
    private val networkService: MovieNetworkService
) {

    operator fun invoke(movieId: String): Flow<DataState<MovieDetails>> = flow {

        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            networkService.getMovieDetails(movieId).collect {
                when (it) {
                    is DataState.Data -> emit(DataState.Data(it.data))
                    is DataState.Loading -> emit(DataState.Loading(it.progressBarState))
                    is DataState.Response -> emit(DataState.Response(it.uiComponent))
                }.exhaustive
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}