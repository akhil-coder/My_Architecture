package com.example.movie.screens.movieDetails

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.movieDetails.MovieDetails

data class MovieDetailsState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movieDetails: MovieDetails? = null,
    val isFavorite: Boolean = false,
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
