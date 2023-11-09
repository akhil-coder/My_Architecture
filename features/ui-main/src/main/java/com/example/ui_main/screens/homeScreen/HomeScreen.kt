package com.example.ui_main.screens.homeScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import coil.ImageLoader
import com.example.components.DefaultScreenUI
import com.example.ui_main.screens.listingScreen.HomeUiEvents
import com.example.ui_main.screens.listingScreen.ListingUiState

@Composable
fun HomeScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: ListingUiState,
    event: (HomeUiEvents) -> Unit,
    navigateToTvShows: () -> Unit,
    openDrawer: () -> Unit
) {

    DefaultScreenUI(networkStatus = networkStatus.value,
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(HomeUiEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
        openDrawer = { openDrawer() },
        content = {

        })

}