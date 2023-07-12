package com.example.myarchitecture

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.ImageLoader
import com.example.components.dialogs.ConfirmDialog
import com.example.components.drawer.AppBarDrawer
import com.example.components.drawer.DrawerBody
import com.example.components.drawer.DrawerHeader
import com.example.components.util.MenuItem
import com.example.myarchitecture.navigation.AppNavGraph
import com.example.myarchitecture.navigation.BottomNavItem
import com.example.myarchitecture.navigation.NavigationProvider
import com.example.navigation.screens.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
    imageLoader: ImageLoader,
    navigationProvider: NavigationProvider,
    networkStatus: MutableState<Boolean>
) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val items = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = TvShowScreen.TvShowList.route,
            icon = Icons.Default.Home,
        ),
        BottomNavItem(
            name = stringResource(R.string.profile),
            route = ProfileScreen.Profile.route,
            icon = Icons.Default.Person,
            badgeCount = 18
        )
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (currentDestination?.route == TvShowScreen.TvShowList.route) {
                AppBarDrawer(
                    title = stringResource(R.string.movie_app)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                items = items,
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(route = TvShowScreen.TvShowList.route)
                        launchSingleTop = true
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader(imageLoader)
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "myList",
                        title = stringResource(R.string.my_movies),
                        contentDescription = "Go to my movies screen",
                        icon = Icons.Default.FavoriteBorder
                    ),
                    MenuItem(
                        id = "settings",
                        title = stringResource(R.string.settings),
                        contentDescription = "Go to my settings screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "utils",
                        title = stringResource(R.string.utils),
                        contentDescription = "Go to utils screen",
                        icon = Icons.Default.Build
                    ),
                ),
                logOut = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                        isDialogVisible.value = true
                    }

                }
            ) { item ->
                Log.e("DRAWABLE::", "MovieScreen: clicked ${item.title}")
                scope.launch {

                    when (item.id) {
                        "myList" -> {
                            navController.navigate(route = MyMoviesScreen.MyMovies.route)
                        }

                        "settings" -> {
                            navController.navigate(route = MainScreen.Settings.route)
                        }

                        "utils" -> {
                            navController.navigate(route = MainScreen.Utils.route)
                        }
                    }
                    scaffoldState.drawerState.close()
                }
            }
        },
        drawerShape = NavShape(0.dp, 0.8f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AppNavGraph(
                navController = navController,
                imageLoader = imageLoader,
                navigationProvider = navigationProvider,
                networkStatus = networkStatus
            )

            if (isDialogVisible.value) {
                ConfirmDialog(
                    isDialogVisible = isDialogVisible.value,
                    title = stringResource(R.string.logout_confirmation),
                    message = stringResource(R.string.logout_confirmation_message),
                    positiveButtonText = stringResource(com.example.ui_main.R.string.ok),
                    negativeButtonText = stringResource(com.example.ui_main.R.string.cancel),
                    onDismissed = {
                        isDialogVisible.value = false
                    },
                    onNegativeButtonClicked = {
                        isDialogVisible.value = false
                    }) {

                    navController.navigate(route = AuthScreen.Login.route) {
                        popUpTo(route = TvShowScreen.TvShowList.route) {
                            inclusive = true
                        }
                    }
                    isDialogVisible.value = false
                }
                /*ShowLogoutConfirmationDialog(isDialogVisible) {
                    navController.navigate(route = AuthScreen.Login.route) {
                        popUpTo(route = MovieScreen.MovieList.route) {
                            inclusive = true
                        }
                    }
                }*/
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val screens = listOf(
        TvShowScreen.TvShowList.route,
        ProfileScreen.Profile.route
    )

    val backStackEntry = navController.currentBackStackEntryAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            modifier = modifier.graphicsLayer {
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                clip = true
            },
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 16.dp,
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { if (!selected) onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge {
                                            Text(
                                                text = item.badgeCount.toString(),
                                                color = Color.White
                                            )
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

class NavShape(
    private val widthOffset: Dp,
    private val scale: Float
) : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            Rect(
                Offset.Zero,
                Offset(
                    size.width * scale + with(density) { widthOffset.toPx() },
                    size.height
                )
            )
        )
    }
}