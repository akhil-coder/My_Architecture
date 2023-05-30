package com.example.navigation.screens

import androidx.navigation.NamedNavArgument

sealed class MyMoviesScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object MyMovies: MyMoviesScreen(
        route = "myMovies",
        arguments = emptyList()
    )
}

