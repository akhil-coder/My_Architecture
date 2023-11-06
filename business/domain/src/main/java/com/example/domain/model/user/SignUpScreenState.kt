package com.example.domain.model.user

data class SignUpScreenState(
    var email: String? = null,
    var password: String? = null,
    val isBadEmail: Boolean = false,
    val isBadPassword: Boolean = false,
    )
