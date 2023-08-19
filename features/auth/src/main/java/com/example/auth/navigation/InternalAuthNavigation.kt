package com.example.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.auth.screens.login.LoginScreen
import com.example.auth.screens.login.LoginScreenViewModel
import com.example.auth.screens.signup.SignupScreen
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.screens.AuthScreen
import com.example.navigation.screens.MovieScreen
import com.google.accompanist.navigation.animation.composable

internal object InternalAuthNavigation : FeatureNavigation {

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
            startDestination = AuthScreen.Login.route, route = GraphRoute.authRoute
        ) {

            composable(
                route = AuthScreen.Login.route
            ) {

                val viewModel = hiltViewModel<LoginScreenViewModel>()

                LoginScreen(state = viewModel.loginUiState.value,
                    events = viewModel::onEventChange,
                    )
            }

            composable(
                route = AuthScreen.Signup.route
            ) {
                SignupScreen(navigateToMovieListsScreen = {
                    navController.navigate(route = MovieScreen.MovieList.route) {
                        popUpTo(route = AuthScreen.Login.route) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}