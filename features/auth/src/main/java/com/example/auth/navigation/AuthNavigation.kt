package com.example.auth.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface AuthNavigation : FeatureNavigation {
}

class AuthNavigationImpl : AuthNavigation {
    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {
        InternalAuthNavigation.registerGraph(navController, navGraphBuilder, imageLoader, width, networkStatus)
    }
}