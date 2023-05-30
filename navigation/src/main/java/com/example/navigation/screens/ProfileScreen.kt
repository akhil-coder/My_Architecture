package com.example.navigation.screens

import androidx.navigation.NamedNavArgument

sealed class ProfileScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object Profile: ProfileScreen(
        route = "profile",
        arguments = emptyList()
    )

    object ProfileEdit: ProfileScreen(
        route = "profileEdit",
        arguments = emptyList()
    )

}

