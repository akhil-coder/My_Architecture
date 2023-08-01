package com.example.profile.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface ProfileNavigation : FeatureNavigation {
}

class ProfileNavigationImpl : ProfileNavigation {

    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {
        InternalProfileNavigation.registerGraph(
            navController,
            navGraphBuilder,
            imageLoader,
            width,
            networkStatus
        )
    }
}