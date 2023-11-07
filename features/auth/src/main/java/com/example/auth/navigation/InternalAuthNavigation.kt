package com.example.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.auth.screens.signIn.SignInScreen
import com.example.auth.screens.signIn.SignInViewModel
import com.example.auth.screens.signUp.SignUpScreen
import com.example.auth.screens.signUp.SignUpViewModel
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
            startDestination = AuthScreen.SignIn.route, route = GraphRoute.authRoute
        ) {

            composable(
                route = AuthScreen.SignIn.route
            ) {
                val viewModel = hiltViewModel<SignInViewModel>()

                SignInScreen(
                    state = viewModel.screenState.value,
                    events = viewModel::onTriggerEvent,
                    navigateToMovieListsScreen = {
                        navController.navigate(route = MovieScreen.MovieList.route) {
                            popUpTo(route = AuthScreen.SignIn.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    navController.navigate(route = AuthScreen.SignUp.route) {
                        popUpTo(route = AuthScreen.SignIn.route) {
                            inclusive = false
                        }
                    }
                }
            }

            composable(
                route = AuthScreen.SignUp.route
            ) {
                val viewModel = hiltViewModel<SignUpViewModel>()

                SignUpScreen(
                    state = viewModel.screenState.value,
                    events = viewModel::onTriggerEvent,
                ) {
                    navController.navigate(route = MovieScreen.MovieList.route) {
                        popUpTo(route = AuthScreen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}