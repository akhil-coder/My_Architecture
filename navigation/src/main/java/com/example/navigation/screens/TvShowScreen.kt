package com.example.navigation.screens

sealed class TvShowScreen(
    val route: String
) {
    object TvShow : TvShowScreen(
        route = "tvShow"
    )
}