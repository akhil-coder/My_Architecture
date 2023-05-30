package com.example.my_list.screens.myMovies.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.movieDetails.MovieDetails
import com.example.interactors.favotite.FavoriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteInteractor: FavoriteInteractor,
    @Named("movieLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _favoriteState = mutableStateOf(FavoriteState())
    val favoriteState: State<FavoriteState> = _favoriteState

    private var recentlyDeletedMovie: MovieDetails? = null

    init {
        onEventChange(FavoriteEvents.GetFavorites)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onEventChange(event: FavoriteEvents){
        when(event){
            FavoriteEvents.GetFavorites -> {
                getAllFavorites()
            }
            FavoriteEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = favoriteState.value.errorQueue
                    queue.remove()
                    _favoriteState.value = favoriteState.value.copy(
                        errorQueue = queue
                    )
                }catch (e: Exception){
                    logger.log("Nothing to remove from queue")
                }
            }
            is FavoriteEvents.RemoveFromFavorite -> {
                viewModelScope.launch {
                    recentlyDeletedMovie = event.movie
                    favoriteInteractor.removeFavorite(event.movie)
                    addToMessageQueue(UIComponent.SnackBar(message = "Movie Removed", action = "Undo"))
                }
            }
            FavoriteEvents.RestoreFavorite -> {
                viewModelScope.launch {
                    recentlyDeletedMovie?.let {
                        favoriteInteractor.addFavorite(it)
                        recentlyDeletedMovie = null
                    }
                }
            }
        }.exhaustive
    }

    private fun getAllFavorites() {
        favoriteInteractor.getFavorites().onEach { dataState ->
            when(dataState){
                is DataState.Data -> {
                    _favoriteState.value = favoriteState.value.copy(
                        movies = dataState.data ?: listOf()
                    )
                }
                is DataState.Loading -> {
                    _favoriteState.value = favoriteState.value.copy(
                        progressBarState = dataState.progressBarState
                    )
                }
                is DataState.Response -> {
                    when(dataState.uiComponent){
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
        val queue = favoriteState.value.errorQueue
        queue.add(uiComponent)
        _favoriteState.value = favoriteState.value.copy(
            errorQueue = queue
        )
    }

}

sealed class FavoriteEvents{
    object GetFavorites: FavoriteEvents()
    data class RemoveFromFavorite(val movie: MovieDetails): FavoriteEvents()
    object RestoreFavorite : FavoriteEvents()
    object OnRemoveHeadFromQueue : FavoriteEvents()
}