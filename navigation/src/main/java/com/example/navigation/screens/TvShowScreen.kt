package com.example.navigation.screens

import androidx.navigation.NamedNavArgument

sealed class TvShowScreen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object TvShowList : TvShowScreen(
        route = "tvShowList",
        arguments = emptyList()
    )

    object TvShowDetails : TvShowScreen(
        route = "tvShowDetails",
        arguments = emptyList()
        )

    object MyTvShow : TvShowScreen(
        route = "myTvShow",
        arguments = emptyList()
    )
}
