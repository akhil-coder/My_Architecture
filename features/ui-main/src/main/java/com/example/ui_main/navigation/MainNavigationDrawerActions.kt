package com.example.ui_main.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


object CelluloidDestination {
    const val HOME_ROUTE = "home"
}

class MainNavigationDrawerActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(CelluloidDestination.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}