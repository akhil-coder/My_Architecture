package com.example.movie.screens.movieList

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.movieList.Movie

data class MovieListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movies: List<Movie> = listOf(),
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
