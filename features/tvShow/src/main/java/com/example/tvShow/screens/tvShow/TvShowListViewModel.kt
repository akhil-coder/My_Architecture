package com.example.tvShow.screens.tvShow

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.tvList.TvShow
import com.example.interactors.tvShow.TvShowInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val tvShowInteractor: TvShowInteractor,
    @Named("movieLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _tvShowListState = mutableStateOf(TvShowListState())
    val tvShowListState: State<TvShowListState> = _tvShowListState

    var discoverTvShowStream: Flow<PagingData<TvShow>>? = null // TODO: Check other repo

    init {
        connectivityObserver.observe().onEach {
            if (it == ConnectivityObserver.Status.Available) {
                onEventChange(TvShowListEvents.DiscoverTvShows)
            }
        }.launchIn(viewModelScope)
    }

    fun printData(){
        Log.d("TvShowListViewModel", "printData: ${_tvShowListState.value.selectedTvShow}")
    }
    fun onEventChange(events: TvShowListEvents) {
        when (events) {
            TvShowListEvents.DiscoverTvShows -> {
                _tvShowListState.value = tvShowListState.value.copy(tvShows = tvShowInteractor.discoverTvShow.getDiscoverMovieStream())
            }

            TvShowListEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = tvShowListState.value.errorQueue
                    queue.remove()
                    _tvShowListState.value = tvShowListState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }

            is TvShowListEvents.OnListItemClick -> {
                _tvShowListState.value = tvShowListState.value.copy(
                    selectedTvShow = events.tvShow
                )
            }
        }.exhaustive
    }

    fun setSelectedTvShow(item: TvShow) {
        onEventChange(TvShowListEvents.OnListItemClick(item))
    }

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = tvShowListState.value.errorQueue
        queue.add(uiComponent)
        _tvShowListState.value = tvShowListState.value.copy(
            errorQueue = queue
        )
    }
}

sealed class TvShowListEvents {
    object DiscoverTvShows : TvShowListEvents()
    data class OnListItemClick(val tvShow: TvShow) : TvShowListEvents()
    object OnRemoveHeadFromQueue : TvShowListEvents()
}