package com.example.my_list.screens.myMovies.favorite

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.movieDetails.MovieDetails

data class FavoriteState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movies: List<MovieDetails> = listOf(),
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
