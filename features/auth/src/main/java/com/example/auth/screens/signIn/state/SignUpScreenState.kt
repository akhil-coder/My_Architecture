package com.example.auth.screens.signIn.state

import androidx.annotation.StringRes

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    val isBadEmail: Boolean = false,
    val password: String = "",
    val isBadPassword: Boolean = false,
    val about: String = "",
    val signedUpUserId: String = "",
    @StringRes val error: Int = 0
)