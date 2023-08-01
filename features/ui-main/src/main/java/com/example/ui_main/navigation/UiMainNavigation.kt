package com.example.ui_main.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface UiMainNavigation : FeatureNavigation {

}

class UiMainNavigationImpl : UiMainNavigation {

    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {
        InternalUiMainNavigation.registerGraph(navController, navGraphBuilder, imageLoader, width, networkStatus, openDrawer)
    }
}