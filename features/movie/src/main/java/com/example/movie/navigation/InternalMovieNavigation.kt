package com.example.movie.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.movie.screens.movieDetails.MovieDetailsScreen
import com.example.movie.screens.movieDetails.MovieDetailsViewModel
import com.example.movie.screens.movieList.MovieListScreen
import com.example.movie.screens.movieList.MovieListViewModel
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.JetsnackNavController
import com.example.navigation.screens.MovieScreen
import com.google.accompanist.navigation.animation.composable

internal object InternalMovieNavigation : FeatureNavigation {

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
            startDestination = MovieScreen.MovieList.route,
            route = GraphRoute.movieRoute
        ) {

            composable(
                route = MovieScreen.MovieList.route,
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -width },
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ) + fadeOut(animationSpec = tween(durationMillis = 300))
                },

                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -width },
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ) + fadeIn(animationSpec = tween(durationMillis = 300))
                }
            ) {

                val movieListViewModel = hiltViewModel<MovieListViewModel>()
                Log.e("Network::", "composable : $networkStatus ")
                MovieListScreen(
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    state = movieListViewModel.movieListState.value,
                    event = movieListViewModel::onEventChange,
                    navigateToDetailsScreen = { id ->
                        navController.navController.navigate(route = "${MovieScreen.MovieDetail.route}/$id")
                    },
                    savedStateHandle = navController.navController.currentBackStackEntry?.savedStateHandle,
                    openDrawer = { openDrawer() }
                )
            }

            composable(
                route = MovieScreen.MovieDetail.route + "/{movieId}",
                arguments = MovieScreen.MovieDetail.arguments,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { width },
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ) + fadeIn(animationSpec = tween(durationMillis = 300))
                },

                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { width },
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ) + fadeOut(animationSpec = tween(durationMillis = 300))
                }
            ) { navBackStack ->
                //val id = navBackStack.arguments?.getString("movieId") ?: ""
                val movieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()
                MovieDetailsScreen(
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    state = movieDetailsViewModel.detailsState.value,
                    event = movieDetailsViewModel::onEventChange,
                    backWithResult = { data ->
                        if (data != null) {
                            navController.navController.previousBackStackEntry?.savedStateHandle?.let {
                                for ((key, value) in data) {
                                    it.set(key, value)
                                }
                            }
                        }
                        navController.navController.popBackStack()
                    },
                    openDrawer = { openDrawer() }
                )
            }
        }
    }
}