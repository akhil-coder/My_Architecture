package com.example.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.JetsnackNavController
import com.example.navigation.screens.ProfileScreen
import com.example.profile.screens.editProfile.ProfileEditScreen
import com.example.profile.screens.viewProfile.ProfileScreen
import com.google.accompanist.navigation.animation.composable


internal object InternalProfileNavigation : FeatureNavigation {

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navController: JetsnackNavController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {

        navGraphBuilder.navigation(
            startDestination = ProfileScreen.Profile.route,
            route = GraphRoute.profileRoute
        ) {

            composable(
                route = ProfileScreen.Profile.route
            ) {
                ProfileScreen(
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    navigateToProfileEditsScreen = {
                        navController.navController.navigate(route = ProfileScreen.ProfileEdit.route)
                    },
                    openDrawer = { openDrawer() }
                )
            }

            composable(
                route = ProfileScreen.ProfileEdit.route
            ) {
                ProfileEditScreen()
            }

        }
    }
}