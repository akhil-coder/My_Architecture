package com.example.auth.screens.signIn

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent

data class SignInScreenState(
    var email: String? = null,
    var password: String = "",
    val isBadEmail: Boolean = false,
    val isBadPassword: Boolean = false,
    val isBadData: Boolean = true,
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf()),
    val progressBarState: ProgressBarState = ProgressBarState.Idle
)
