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

    object SignIn: AuthScreen(
        route = "signIn",
        arguments = emptyList()
    )

    object SignUp: AuthScreen(
        route = "signUp",
        arguments = emptyList()
    )


}

