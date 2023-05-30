package com.example.myarchitecture.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
){
    /*object Splash: Screen(
        route = "splash",
        arguments = emptyList()
    )

    object Login: Screen(
        route = "login",
        arguments = emptyList()
    )

    object Signup: Screen(
        route = "signup",
        arguments = emptyList()
    )

    object MovieList: Screen(
        route = "movieList",
        arguments = emptyList()
    )

    object MovieDetail: Screen(
        route = "movieDetail",
        arguments = listOf(
            navArgument(name = "movieId"){
                type = NavType.StringType
                defaultValue = ""
            }
        )
    )

    object Profile: Screen(
        route = "profile",
        arguments = emptyList()
    )

    object ProfileEdit: Screen(
        route = "profileEdit",
        arguments = emptyList()
    )

    object MyMovies: Screen(
        route = "myMovies",
        arguments = emptyList()
    )

    object Settings: Screen(
        route = "settings",
        arguments = emptyList()
    )*/

}
