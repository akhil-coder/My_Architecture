package com.example.my_list.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.my_list.screens.myMovies.MyMoviesScreen
import com.example.my_list.screens.myMovies.favorite.FavoriteViewModel
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.screens.MyMoviesScreen
import com.google.accompanist.navigation.animation.composable

internal object InternalMyListNavigation : FeatureNavigation {

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit
    ) {

        navGraphBuilder.navigation(
            startDestination = MyMoviesScreen.MyMovies.route,
            route = GraphRoute.myListRoute
        ) {

            composable(
                route = MyMoviesScreen.MyMovies.route
            ) {

                val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

                MyMoviesScreen(
                    popBack = {
                        navController.popBackStack()
                    },
                    imageLoader = imageLoader,
                    stateFav = favoriteViewModel.favoriteState.value,
                    eventFav = favoriteViewModel::onEventChange,
                    openDrawer = { openDrawer() }
                )
            }

        }
    }
}