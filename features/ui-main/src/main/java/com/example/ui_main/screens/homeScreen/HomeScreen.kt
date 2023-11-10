package com.example.ui_main.screens.homeScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import coil.ImageLoader
import com.example.components.DefaultScreenUI

@Composable
fun HomeScreen(
    state: HomeScreenState,
    event: (HomeUiEvents) -> Unit,
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    openDrawer: () -> Unit,
) {

    DefaultScreenUI(
        networkStatus = networkStatus.value,
        openDrawer = openDrawer,
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(HomeUiEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
        content = {
            Text(text = "Hello")
        },
        drawerEnable = true
    )

}