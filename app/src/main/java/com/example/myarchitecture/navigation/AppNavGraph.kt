package com.example.myarchitecture.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.navigation.GraphRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.Job

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
    navigationProvider: NavigationProvider,
    networkStatus: MutableState<Boolean>,
    widthSizeClass: WindowWidthSizeClass,
    openDrawer:  () -> Unit = {}
) {

    BoxWithConstraints {

        val width = constraints.maxWidth / 2

        AnimatedNavHost(
            navController = navController, startDestination = GraphRoute.uiMainRoute
        ) {

            navigationProvider.uiMainNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus
            )

            navigationProvider.tvShowScreenNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus, openDrawer
            )

            navigationProvider.authNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus
            )

            navigationProvider.movieNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus
            )

            navigationProvider.profileNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus
            )

            navigationProvider.myListNavigation.registerGraph(
                navController, this, imageLoader, width, networkStatus
            )


            /*composable(
                route = Screen.Splash.route
            ) {
                SplashScreen(
                    navigateToLoginsScreen = {
                        navController.navigate(route = Screen.Login.route) {
                            popUpTo(route = Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToMovieListsScreen = {
                        navController.navigate(route = Screen.MovieList.route) {
                            popUpTo(route = Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.Login.route
            ) {
                LoginScreen(
                    navigateToSignupScreen = {
                        navController.navigate(route = Screen.Signup.route) {
                            popUpTo(route = Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToMovieListsScreen = {
                        navController.navigate(route = Screen.MovieList.route) {
                            popUpTo(route = Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.Signup.route
            ) {
                SignupScreen(
                    navigateToMovieListsScreen = {
                        navController.navigate(route = Screen.MovieList.route) {
                            popUpTo(route = Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }



            composable(
                route = Screen.MovieList.route,
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
                MovieListScreen(
                    imageLoader = imageLoader,
                    state = movieListViewModel.movieListState.value,
                    event = movieListViewModel::onEventChange,
                    navigateToDetailsScreen = { id ->
                        navController.navigate(route = "${Screen.MovieDetail.route}/$id")
                    }
                )
            }

            composable(
                route = Screen.MovieDetail.route + "/{movieId}",
                arguments = Screen.MovieDetail.arguments,
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
                    state = movieDetailsViewModel.detailsState.value,
                    event = movieDetailsViewModel::onEventChange,
                )
            }


            composable(
                route = Screen.Profile.route
            ) {
                ProfileScreen(
                    imageLoader = imageLoader,
                    navigateToProfileEditsScreen = {
                        navController.navigate(route = Screen.ProfileEdit.route)
                    }
                )
            }

            composable(
                route = Screen.ProfileEdit.route
            ) {
                ProfileEditScreen()
            }

            composable(
                route = Screen.MyMovies.route
            ) {

                val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

                MyMoviesScreen(
                    popBack = {
                        navController.popBackStack()
                    },
                    imageLoader = imageLoader,
                    stateFav = favoriteViewModel.favoriteState.value,
                    eventFav = favoriteViewModel::onEventChange,
                )
            }

            composable(
                route = Screen.Settings.route
            ) {
                val settingsViewModel = hiltViewModel<SettingsViewModel>()
                SettingsScreen(
                    isDarkTheme = settingsViewModel.themeState.value,
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
                    }
                )
            }*/
        }
    }


}