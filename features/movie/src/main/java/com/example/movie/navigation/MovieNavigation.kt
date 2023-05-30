package com.example.movie.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation

interface MovieNavigation : FeatureNavigation {
}

class MovieNavigationImpl : MovieNavigation {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>
    ) {
        InternalMovieNavigation.registerGraph(
            navController,
            navGraphBuilder,
            imageLoader,
            width,
            networkStatus
        )
    }
}