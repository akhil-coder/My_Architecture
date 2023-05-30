package com.example.auth.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation

interface AuthNavigation : FeatureNavigation {
}

class AuthNavigationImpl : AuthNavigation {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>
    ) {
        InternalAuthNavigation.registerGraph(navController, navGraphBuilder, imageLoader, width, networkStatus)
    }
}