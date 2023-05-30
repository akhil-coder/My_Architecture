package com.example.navigation.screens

import androidx.navigation.NamedNavArgument

sealed class AuthScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object Login: AuthScreen(
        route = "login",
        arguments = emptyList()
    )

    object Signup: AuthScreen(
        route = "signup",
        arguments = emptyList()
    )

}

