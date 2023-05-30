package com.example.interactors.favotite

import com.example.core.domain.DataState
import com.example.core.domain.ProgressBarState
import com.example.core.domain.UIComponent
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.cache.FavoriteDbService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavorites constructor(
    private val favoriteDbService: FavoriteDbService
) {

    operator fun invoke() : Flow<DataState<List<MovieDetails>>> = flow {

        try {
            //emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            favoriteDbService.getFavoriteMovies().collect{
                emit(DataState.Data(it))
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
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }
}