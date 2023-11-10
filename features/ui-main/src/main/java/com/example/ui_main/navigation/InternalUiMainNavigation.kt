package com.example.ui_main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.navigation.FeatureNavigation
import com.example.navigation.GraphRoute
import com.example.navigation.screens.AuthScreen
import com.example.navigation.screens.MainScreen
import com.example.navigation.screens.TvShowScreen
import com.example.ui_main.screens.homeScreen.HomeScreen
import com.example.ui_main.screens.homeScreen.HomeScreenViewModel
import com.example.ui_main.screens.listingScreen.ListingScreen
import com.example.ui_main.screens.listingScreen.ListingScreenViewModel
import com.example.ui_main.screens.settings.SettingsScreen
import com.example.ui_main.screens.settings.SettingsViewModel
import com.example.ui_main.screens.splash.SplashScreen
import com.example.ui_main.screens.util.UtilScreen
import com.example.ui_main.screens.util.UtilViewModel
import com.google.accompanist.navigation.animation.composable

internal object InternalUiMainNavigation : FeatureNavigation {
    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        imageLoader: ImageLoader,
        width: Int,
        networkStatus: MutableState<Boolean>,
        openDrawer: () -> Unit,
    ) {

        navGraphBuilder.navigation(
            startDestination = MainScreen.HomeScreen.route, route = GraphRoute.uiMainRoute
        ) {

            composable(
                route = MainScreen.Splash.route
            ) {
                SplashScreen(navigateToSignInScreen = {
                    navController.navigate(route = AuthScreen.SignIn.route) {
                        popUpTo(route = MainScreen.Splash.route) {
                            inclusive = true
                        }
                    }
                })
            }

            composable(
                route = MainScreen.HomeScreen.route
            ) {
                val viewModel = hiltViewModel<HomeScreenViewModel>()
                HomeScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEventChange,
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    openDrawer = openDrawer

                )
            }

            composable(
                route = MainScreen.ListingScreen.route
            ) {
                val viewModel = hiltViewModel<ListingScreenViewModel>()
                ListingScreen(state = viewModel.listingUiState.value,
                    event = viewModel::onEventChange,
                    imageLoader = imageLoader,
                    networkStatus = networkStatus,
                    openDrawer = openDrawer,
                    navigateToTvShows = {
                        navController.navigate(route = TvShowScreen.TvShowList.route) {
                            popUpTo(route = MainScreen.ListingScreen.route) {
                                inclusive = false
                            }
                        }
                    })
            }

            composable(
                route = MainScreen.Settings.route
            ) {
                val settingsViewModel = hiltViewModel<SettingsViewModel>()
                SettingsScreen(isDarkTheme = settingsViewModel.themeState.value,
                    appLanguage = settingsViewModel.languageState.value,
                    isLanguageDialogShone = settingsViewModel.isLanguageDialogShone,
                    popBack = {
                        navController.popBackStack()
                    },
                    setTheme = {
                        settingsViewModel.setTheme(it)
                    },
                    setLanguage = {
                        settingsViewModel.setLanguage(it)
                    },
                    showLanguageDialog = {
                        settingsViewModel.showLanguageDialog()
                    },
                    dismissLanguageDialog = {
                        settingsViewModel.dismissLanguageDialog()
                    })
            }

            composable(
                route = MainScreen.Utils.route
            ) {
                val utilsViewModel = hiltViewModel<UtilViewModel>()
                UtilScreen(
                    state = utilsViewModel.utilState.value,
                    event = utilsViewModel::onEventChange,
                    popBack = {
                        navController.popBackStack()
                    },
                    openDrawer = openDrawer
                )
            }
        }
    }
}