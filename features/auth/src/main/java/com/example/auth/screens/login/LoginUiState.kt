package com.example.auth.screens.login

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.core.domain.UIComponentState
import com.example.domain.model.homeUi.HomeUi

data class LoginUiState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val welcomeDialogState: UIComponentState = UIComponentState.Hide, // show/hide the filter dialog

    var userName: String = "",
    var password: String = "",
    var confirmPassword: String = "",

    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
