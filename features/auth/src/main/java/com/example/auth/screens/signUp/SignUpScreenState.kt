package com.example.auth.screens.signUp

data class SignUpScreenState(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var passwordConfirm: String = "",
    var checkBox: Boolean? = null,
    val isBadFirstName: Boolean = false,
    val isBadLastName: Boolean = false,
    val isBadEmail: Boolean = false,
    val isBadPassword: Boolean = false,
    val isBadPasswordConfirm: Boolean = false,
)
