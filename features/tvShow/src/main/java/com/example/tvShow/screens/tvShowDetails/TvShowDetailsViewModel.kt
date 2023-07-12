package com.example.tvShow.screens.tvShowDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.tvShowDetails.TvShowDetails
import com.example.interactors.favotite.FavoriteInteractor
import com.example.interactors.movies.MovieInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
    private val favoriteInteractor: FavoriteInteractor,
    @Named("movieLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailsState = mutableStateOf(TvShowDetailsState())
    val detailsState: State<TvShowDetailsState> = _detailsState

    init {
        savedStateHandle.get<TvShowDetails>("clickedTvShow")?.let { tvShowDetails ->
            onEventChange(TvShowDetailsEvents.SetMovieDetails(tvShowDetails))
        }
    }

    fun onEventChange(events: TvShowDetailsEvents) {
        when (events) {
            is TvShowDetailsEvents.SetMovieDetails -> setMovieDetails(events.tvShowDetails)
            else -> {}
        }.exhaustive
    }

    private fun setMovieDetails(tvShowDetails: TvShowDetails) {
        _detailsState.value = detailsState.value.copy(
            tvShowDetails = tvShowDetails
        )
    }
}

sealed class TvShowDetailsEvents {
    data class SetMovieDetails(val tvShowDetails: TvShowDetails) : TvShowDetailsEvents()
    data class CheckFavoriteStatus(val id: Int) : TvShowDetailsEvents()
    object AddToFavorite : TvShowDetailsEvents()
    object RemoveFromFavorite : TvShowDetailsEvents()
    object OnRemoveHeadFromQueue : TvShowDetailsEvents()
}