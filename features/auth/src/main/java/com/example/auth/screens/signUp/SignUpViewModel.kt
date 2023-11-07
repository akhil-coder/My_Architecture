package com.example.auth.screens.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.components.regex.RegexCredentialsValidator
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.domain.model.user.SignUpScreenState
import com.example.preferences.BasePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val basePreferencesManager: BasePreferencesManager,
    @Named("authLogger") private val logger: Logger
) : ViewModel() {

    private val _screenState = mutableStateOf(SignUpScreenState())
    val screenState: State<SignUpScreenState> = _screenState

    private val credentialsValidator = RegexCredentialsValidator()

    fun onTriggerEvent(events: SignUpUIEvents) {
        when (events) {
            is SignUpUIEvents.UpdateFirstName -> updateFirstName(events.firstName)
            is SignUpUIEvents.UpdateLastName -> updateLastName(events.lastName)
            is SignUpUIEvents.UpdateEmail -> updateEmail(events.email)
            is SignUpUIEvents.UpdatePassword -> updatePassword(events.password)
            is SignUpUIEvents.UpdatePasswordConfirm -> updatePasswordConfirm(events.passwordConfirm)
            is SignUpUIEvents.UpdateCheckBox -> updateCheckBox(events.checked)
            SignUpUIEvents.SignUp -> signUp()
            SignUpUIEvents.OnRemoveHeadFromQueue -> TODO()

        }.exhaustive
    }

    private fun signUp() {
        // TODO: API call
    }

    private fun updateFirstName(firstName: String) {
        val validateValue = credentialsValidator.validateValue(name = firstName)
        if (validateValue) {
            _screenState.value =
                screenState.value.copy(firstName = firstName, isBadFirstName = false)
        } else _screenState.value = screenState.value.copy(isBadFirstName = true)
    }

    private fun updateLastName(lastName: String) {
        val validateValue = credentialsValidator.validateValue(name = lastName)
        if (validateValue) {
            _screenState.value = screenState.value.copy(lastName = lastName, isBadLastName = false)
        } else _screenState.value = screenState.value.copy(isBadLastName = true)
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
        } else _screenState.value =
            screenState.value.copy(password = password, isBadPassword = true)

        if (screenState.value.passwordConfirm?.isBlank() != true) { // Checks both passwords for a match
            if (password == screenState.value.passwordConfirm) _screenState.value =
                screenState.value.copy(
                    isBadPasswordConfirm = false
                )
            else {
                _screenState.value = screenState.value.copy(
                    isBadPasswordConfirm = true
                )
            }
        }

    }

    private fun updatePasswordConfirm(passwordConfirm: String) {
        val validateValue = credentialsValidator.validateValue(password = passwordConfirm)
        if (validateValue && _screenState.value.password == passwordConfirm) {
            _screenState.value = screenState.value.copy(
                passwordConfirm = passwordConfirm, isBadPasswordConfirm = false
            )
        } else _screenState.value =
            screenState.value.copy(passwordConfirm = passwordConfirm, isBadPasswordConfirm = true)
    }

    private fun updateCheckBox(checked: Boolean) {
        _screenState.value = screenState.value.copy(checkBox = true)
    }

}

sealed class SignUpUIEvents {
    data class UpdateFirstName(val firstName: String) : SignUpUIEvents()

    data class UpdateLastName(val lastName: String) : SignUpUIEvents()

    data class UpdateEmail(val email: String) : SignUpUIEvents()

    data class UpdatePassword(val password: String) : SignUpUIEvents()

    data class UpdatePasswordConfirm(val passwordConfirm: String) : SignUpUIEvents()

    data class UpdateCheckBox(val checked: Boolean) : SignUpUIEvents()

    object SignUp : SignUpUIEvents()

    object OnRemoveHeadFromQueue : SignUpUIEvents()
}