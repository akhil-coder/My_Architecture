package com.example.tvShow.screens.tvShowDetails

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.model.tvShowDetails.TvShowDetails

data class TvShowDetailsState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val tvShowDetails: TvShowDetails? = null,
    val isFavorite: Boolean = false,
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
