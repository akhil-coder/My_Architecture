package com.example.profile.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.FeatureNavigation

interface ProfileNavigation : FeatureNavigation {
}

class ProfileNavigationImpl : ProfileNavigation {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>
    ) {
        InternalProfileNavigation.registerGraph(navController, navGraphBuilder, imageLoader, width, networkStatus)
    }
}