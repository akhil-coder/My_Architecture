package com.example.tvShow.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface TvShowScreenNavigation : FeatureNavigation {
}

class TvShowScreenNavigationImpl : TvShowScreenNavigation {
    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {
        InternalTvShowScreenNavigation.registerGraph(
            navController,
            navGraphBuilder,
            imageLoader,
            width,
            networkStatus,
            openDrawer
        ) // This one.
    }
}