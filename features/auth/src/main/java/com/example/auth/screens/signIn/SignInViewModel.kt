package com.example.auth.screens.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.regex.RegexCredentialsValidator
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.user.SignInScreenState
import com.example.preferences.BasePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val basePreferencesManager: BasePreferencesManager,
    @Named("authLogger") private val logger: Logger
) : ViewModel() {

    private val _screenState = mutableStateOf(SignInScreenState())
    val screenState: State<SignInScreenState> = _screenState

    private val credentialsValidator = RegexCredentialsValidator()

    fun onTriggerEvent(events: SignInUIEvents) {
        when (events) {
            SignInUIEvents.OnRemoveHeadFromQueue -> TODO()
            is SignInUIEvents.UpdatePassword -> updatePassword(events.password)
            is SignInUIEvents.UpdateEmail -> updateEmail(events.email)
            is SignInUIEvents.SignIn -> setUserDetails(screenState.value)
        }.exhaustive
    }

    private fun setUserDetails(userDetails: SignInScreenState) {
        viewModelScope.launch {
            basePreferencesManager.setUser(userDetails)
        }
    }

    private fun updateEmail(email: String) {
        val validateValue = credentialsValidator.validateValue(email = email)
        if (validateValue) {
            _screenState.value = screenState.value.copy(email = email, isBadEmail = false)
        } else _screenState.value = screenState.value.copy(isBadEmail = true)
    }

    private fun updatePassword(password: String) {
        val validateValue = credentialsValidator.validateValue(password = password)
        if (validateValue) {
            _screenState.value = screenState.value.copy(password = password, isBadPassword = false)
        } else _screenState.value = screenState.value.copy(isBadPassword = true)
    }
}

sealed class SignInUIEvents {
    data class UpdateEmail(val email: String) : SignInUIEvents()

    data class UpdatePassword(val password: String) : SignInUIEvents()

    object SignIn : SignInUIEvents()
    object OnRemoveHeadFromQueue : SignInUIEvents()
}