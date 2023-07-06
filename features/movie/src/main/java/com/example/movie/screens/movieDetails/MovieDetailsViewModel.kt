package com.example.movie.screens.movieDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.interactors.favotite.FavoriteInteractor
import com.example.interactors.movies.MovieInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
    private val favoriteInteractor: FavoriteInteractor,
    @Named("movieLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailsState = mutableStateOf(MovieDetailsState())
    val detailsState: State<MovieDetailsState> = _detailsState

    init {
        savedStateHandle.get<String>("movieId")?.let { id ->
            if (id.isNotEmpty()) {
                onEventChange(MovieDetailsEvents.GetMovieDetails(id))
                onEventChange(MovieDetailsEvents.CheckFavoriteStatus(id.toInt()))
            } else {
                logger.log("movie not found")
            }
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onEventChange(event: MovieDetailsEvents) {
        when (event) {
            is MovieDetailsEvents.GetMovieDetails -> getDetails(event.movieId)
            is MovieDetailsEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = detailsState.value.errorQueue
                    queue.remove()
                    _detailsState.value = detailsState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }
            is MovieDetailsEvents.CheckFavoriteStatus -> {
                viewModelScope.launch {
                    val movie = favoriteInteractor.getFavoriteById(event.id)
                    _detailsState.value = detailsState.value.copy(
                        isFavorite = movie != null
                    )
                }
            }
            MovieDetailsEvents.AddToFavorite -> {
                viewModelScope.launch {
                    if (_detailsState.value.movieDetails != null) {
                        favoriteInteractor.addFavorite(_detailsState.value.movieDetails!!)
                        onEventChange(MovieDetailsEvents.CheckFavoriteStatus(_detailsState.value.movieDetails!!.id))
                    }
                }
            }
            MovieDetailsEvents.RemoveFromFavorite -> {
                viewModelScope.launch {
                    if (_detailsState.value.movieDetails != null) {
                        favoriteInteractor.removeFavorite(_detailsState.value.movieDetails!!)
                        onEventChange(MovieDetailsEvents.CheckFavoriteStatus(_detailsState.value.movieDetails!!.id))
                    }
                }
            }
        }.exhaustive
    }

    private fun getDetails(movieId: String) {
        movieInteractor.getMovieDetails(movieId).onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    _detailsState.value = detailsState.value.copy(
                        movieDetails = dataState.data
                    )
                    logger.log("movie detail : ${dataState.data?.originalTitle}")
                }
                is DataState.Loading -> {
                    _detailsState.value = detailsState.value.copy(
                        progressBarState = dataState.progressBarState
                    )
                }
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            addToMessageQueue(dataState.uiComponent)
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                        is UIComponent.SnackBar -> {

                        }
                    }.exhaustive
                }
            }.exhaustive
        }.launchIn(viewModelScope)
    }

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = detailsState.value.errorQueue
        queue.add(uiComponent)
        _detailsState.value = detailsState.value.copy(
            errorQueue = queue
        )
    }
}

sealed class MovieDetailsEvents {
    data class GetMovieDetails(val movieId: String) : MovieDetailsEvents()
    data class CheckFavoriteStatus(val id: Int) : MovieDetailsEvents()
    object AddToFavorite : MovieDetailsEvents()
    object RemoveFromFavorite : MovieDetailsEvents()
    object OnRemoveHeadFromQueue : MovieDetailsEvents()
}