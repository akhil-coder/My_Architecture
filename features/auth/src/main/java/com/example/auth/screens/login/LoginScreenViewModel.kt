package com.example.auth.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.domain.UIComponentState
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.interactors.homeUi.HomeUiInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    @Named("mainLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _LoginUiState = mutableStateOf(LoginUiState())
    val loginUiState: State<LoginUiState> = _LoginUiState

    fun onEventChange(event: LoginUIEvents) {
        when (event) {
            is LoginUIEvents.LogIn -> {

            }

            is LoginUIEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = loginUiState.value.errorQueue
                    queue.remove()
                    _LoginUiState.value = loginUiState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }

            is LoginUIEvents.UpdateWelcomeDialogState -> {
                _LoginUiState.value = _LoginUiState.value.copy(welcomeDialogState = event.uiComponentState)
            }
        }.exhaustive
    }

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = loginUiState.value.errorQueue
        queue.add(uiComponent)
        _LoginUiState.value = loginUiState.value.copy(
            errorQueue = queue
        )
    }
}

sealed class LoginUIEvents {
    object LogIn : LoginUIEvents()
    data class UpdateWelcomeDialogState(
        val uiComponentState: UIComponentState
    ): LoginUIEvents()
    object OnRemoveHeadFromQueue : LoginUIEvents()
}