package com.example.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.auth.screens.login.LoginScreen
import com.example.auth.screens.signup.SignupScreen
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.screens.AuthScreen
import com.example.navigation.screens.MainScreen
import com.example.navigation.screens.MovieScreen
import com.google.accompanist.navigation.animation.composable

internal object InternalAuthNavigation : FeatureNavigation {

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>
    ) {
        navGraphBuilder.navigation(
            startDestination = AuthScreen.Login.route, route = GraphRoute.authRoute
        ) {

            composable(
                route = AuthScreen.Login.route
            ) {
                LoginScreen(navigateToSignupScreen = {
                    navController.navigate(route = AuthScreen.Signup.route) {
                        popUpTo(route = MainScreen.Splash.route) {
                            inclusive = true
                        }
                    }
                }, navigateToMovieListsScreen = {
                    navController.navigate(route = MainScreen.HomeScreen.route) {
                        popUpTo(route = MainScreen.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                })
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