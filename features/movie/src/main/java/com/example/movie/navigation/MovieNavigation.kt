package com.example.movie.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface MovieNavigation : FeatureNavigation {
}

class MovieNavigationImpl : MovieNavigation {
    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
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