package com.example.tvShow.screens.tvShow

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.movieList.Movie
import com.example.domain.model.tvList.TvShow

data class TvShowListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val tvShows: List<TvShow> = listOf(),
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
