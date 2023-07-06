package com.example.tvShow.screens.tvShow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.tvList.TvShow
import com.example.interactors.tvShow.TvShowInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    val tvShowListStateState: State<TvShowListState> = _tvShowListState

    var discoverTvShowStream: Flow<PagingData<TvShow>>? = null

    init {
        connectivityObserver.observe().onEach {
            if (it == ConnectivityObserver.Status.Available && _tvShowListState.value.tvShows.isEmpty()) {
                onEventChange(TvShowListEvents.DiscoverTvShows)
            }
        }.launchIn(viewModelScope)
    }

    fun onEventChange(events: TvShowListEvents) {
        when (events) {
            TvShowListEvents.DiscoverTvShows -> {
                discoverTvShowStream = tvShowInteractor.discoverTvShow.getDiscoverMovieStream().cachedIn(viewModelScope)

            }

            TvShowListEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = tvShowListStateState.value.errorQueue
                    queue.remove()
                    _tvShowListState.value = tvShowListStateState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }
        }.exhaustive
    }

    /*  private fun discoverTvShow() {
          tvShowInteractor.discoverTvShow().onEach { dataState ->
              when (dataState) {
                  is DataState.Data -> {
                      _tvShowListState.value = tvShowListStateState.value.copy(
                          tvShows = dataState.data?.shuffled() ?: listOf()
                      )
                      logger.log("Movie list size: ${tvShowListStateState.value.tvShows.size}")
                  }

                  is DataState.Loading -> {
                      _tvShowListState.value = tvShowListStateState.value.copy(
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
      }*/

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = tvShowListStateState.value.errorQueue
        queue.add(uiComponent)
        _tvShowListState.value = tvShowListStateState.value.copy(
            errorQueue = queue
        )
    }
}

sealed class TvShowListEvents {
    object DiscoverTvShows : TvShowListEvents()
    object OnRemoveHeadFromQueue : TvShowListEvents()
}