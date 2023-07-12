package com.example.navigation.screens

import androidx.navigation.NamedNavArgument

sealed class MainScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object Splash: MainScreen(
        route = "splash",
        arguments = emptyList()
    )

    object HomeScreen : MainScreen(
        route = "homeScreen",
        arguments = emptyList()
    )

    object Settings: MainScreen(
        route = "settings",
        arguments = emptyList()
    )

    object Utils: MainScreen(
        route = "utils",
        arguments = emptyList()
    )
}
