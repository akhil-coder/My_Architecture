package com.example.movie.screens.movieList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.UiEvent
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.interactors.movies.MovieInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
    @Named("movieLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _movieListState = mutableStateOf(MovieListState())
    val movieListState: State<MovieListState> = _movieListState

    private val _networkState = mutableStateOf(ConnectivityObserver.Status.Unavailable)
    val networkState: State<ConnectivityObserver.Status> = _networkState

    init {

        connectivityObserver.observe().onEach {
            if(it == ConnectivityObserver.Status.Available && _movieListState.value.movies.isEmpty()){
                onEventChange(MovieListEvents.GetAllMovies)
            }
        }.launchIn(viewModelScope)

    }


    fun onEventChange(events: MovieListEvents) {
        when(events){
            MovieListEvents.GetAllMovies -> {
                getMovieList()
            }
            MovieListEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = movieListState.value.errorQueue
                    queue.remove()
                    _movieListState.value = movieListState.value.copy(
                        errorQueue = queue
                    )
                }catch (e: Exception){
                    logger.log("Nothing to remove from queue")
                }
            }
        }.exhaustive
    }

    private fun getMovieList() {
        movieInteractor.getAllMovies().onEach { dataState ->
            when(dataState){
                is DataState.Data -> {
                    _movieListState.value = movieListState.value.copy(
                        movies = dataState.data?.shuffled() ?: listOf()
                    )
                    logger.log("Movie list size: ${movieListState.value.movies.size}")
                }
                is DataState.Loading -> {
                    _movieListState.value = movieListState.value.copy(
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
        val queue = movieListState.value.errorQueue
        queue.add(uiComponent)
        _movieListState.value = movieListState.value.copy(
            errorQueue = queue
        )
    }

}

sealed class MovieListEvents{
    object GetAllMovies: MovieListEvents()
    object OnRemoveHeadFromQueue : MovieListEvents()
}