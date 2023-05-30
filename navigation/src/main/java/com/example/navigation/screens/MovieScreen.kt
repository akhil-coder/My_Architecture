package com.example.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class MovieScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object MovieList: MovieScreen(
        route = "movieList",
        arguments = emptyList()
    )

    object MovieDetail: MovieScreen(
        route = "movieDetail",
        arguments = listOf(
            navArgument(name = "movieId"){
                type = NavType.StringType
                defaultValue = ""
            }
        )
    )

}

