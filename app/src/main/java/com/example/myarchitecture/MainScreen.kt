package com.example.myarchitecture

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.ImageLoader
import com.example.components.AppDrawer
import com.example.myarchitecture.navigation.AppNavGraph
import com.example.myarchitecture.navigation.NavigationProvider
import com.example.navigation.NavigationDrawerNavigationActions
import com.example.navigation.screens.MainScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
    imageLoader: ImageLoader,
    navigationProvider: NavigationProvider,
    networkStatus: MutableState<Boolean>,
    widthSizeClass: WindowWidthSizeClass
) {
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainScreen.HomeScreen.route

    val navigationActions = remember(navController) {
        NavigationDrawerNavigationActions(navController)
    }

    val coroutineScope = rememberCoroutineScope()

    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
    val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

    val isDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(currentRoute = currentRoute,
                navigateToHome = navigationActions.navigateToHome,
                closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } })
        }, drawerState = sizeAwareDrawerState,
        // Only enable opening the drawer via gestures if the screen is not expanded
        gesturesEnabled = when (currentRoute) {
            "signIn", "signUp" -> false
            else -> {
                true
            }
        }
    ) {
        Row {
            AppNavGraph(widthSizeClass = widthSizeClass,
                navController = navController,
                imageLoader = imageLoader,
                navigationProvider = navigationProvider,
                networkStatus = networkStatus,
                openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } })
        }
    }
/*
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val screens = listOf(
        MainScreen.HomeScreen.route, ProfileScreen.Profile.route
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
            backgroundColor = MaterialTheme.colorScheme.surface,
            elevation = 16.dp,
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(selected = selected,
                    onClick = { if (!selected) onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgedBox(badge = {
                                    Badge {
                                        Text(
                                            text = item.badgeCount.toString(), color = Color.White
                                        )
                                    }
                                }) {
                                    Icon(
                                        imageVector = item.icon, contentDescription = item.name
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon, contentDescription = item.name
                                )
                            }
                            if (selected) {
                                Text(
                                    text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp
                                )
                            }
                        }
                    })
            }
        }
    }
}*/
}

class NavShape(
    private val widthOffset: Dp, private val scale: Float
) : Shape {

    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Rectangle(
            Rect(
                Offset.Zero, Offset(
                    size.width * scale + with(density) { widthOffset.toPx() }, size.height
                )
            )
        )
    }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
/**
 * Determine the drawer state to pass to the modal drawer.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}

