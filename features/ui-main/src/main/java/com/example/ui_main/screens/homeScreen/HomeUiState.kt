package com.example.ui_main.screens.homeScreen

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.domain.model.homeUi.HomeUi

data class HomeUiState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val homeUi: HomeUi? = null,
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
