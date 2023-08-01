package com.example.my_list.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.JetsnackNavController

interface MyListNavigation : FeatureNavigation {
}

class MyListNavigationImpl : MyListNavigation {
    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {
        InternalMyListNavigation.registerGraph(
            navController,
            navGraphBuilder,
            imageLoader,
            width,
            networkStatus
        )
    }
}