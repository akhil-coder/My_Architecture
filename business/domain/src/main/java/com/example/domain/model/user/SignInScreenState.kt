package com.example.domain.model.user

data class SignInScreenState(
    var email: String? = null,
    var password: String = "",
    val isBadEmail: Boolean = false,
    val isBadPassword: Boolean = false,
    val isBadData: Boolean = true
)
