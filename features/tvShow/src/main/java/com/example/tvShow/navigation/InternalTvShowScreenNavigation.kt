package com.example.tvShow.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.screens.TvShowScreen
import com.example.tvShow.screens.tvShow.TvShowListScreen
import com.example.tvShow.screens.tvShow.TvShowListViewModel
import com.google.accompanist.navigation.animation.composable

internal object InternalTvShowScreenNavigation : FeatureNavigation {

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>
    ) {
        navGraphBuilder.navigation(
            startDestination = TvShowScreen.TvShow.route, route = GraphRoute.tvShowRoute
        ) {
            composable(route = TvShowScreen.TvShow.route, exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -width }, animationSpec = tween(
                        durationMillis = 300,
                    )
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            },

                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -width }, animationSpec = tween(
                            durationMillis = 300,
                        )
                    ) + fadeIn(animationSpec = tween(durationMillis = 300))
                }) {
                val tvShowListViewModel = hiltViewModel<TvShowListViewModel>()
                Log.e("Network::", "composable : $networkStatus ")
                TvShowListScreen(
                    navController = navController,
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    state = tvShowListViewModel.tvShowListStateState.value,
                    event = tvShowListViewModel::onEventChange,
                    savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
                )
            }
        }
    }
}