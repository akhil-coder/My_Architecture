package com.example.ui_main.screens.listingScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.example.components.DefaultScreenUI
import com.example.ui_main.R
import com.example.ui_main.screens.sdui.SDUIDisplayManager
import com.example.ui_main.screens.util.ButtonBlue
import com.example.ui_main.screens.util.LightRed

@Composable
fun ListingScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: ListingUiState,
    event: (ListingScreenUiEvents) -> Unit,
    navigateToTvShows: () -> Unit,
    openDrawer: () -> Unit,
) {

    SDUIDisplayManager.navigateToTvShows = navigateToTvShows

    DefaultScreenUI(
        networkStatus = networkStatus.value,
        openDrawer = openDrawer,
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(ListingScreenUiEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
        content = {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ) {
                        item {
                            state.homeUi?.let { it ->
                                SDUIDisplayManager.RootDisplay(
                                    it
                                )
                            }
                        }
                    }
                }
            }
        },
        drawerEnable = false
    )
}

@Composable
fun TilesSection(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemTiles("Movies", R.drawable.baseline_movie_24, onClick)
        ItemTiles("Tv Shows", R.drawable.baseline_live_tv_24, onClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTiles(
    tileName: String = "Name",
    @DrawableRes iconId: Int = R.drawable.baseline_movie_24,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .padding(10.dp)
        .defaultMinSize(minWidth = 150.dp, minHeight = 150.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        onClick = {
            onClick()
        }) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Tile Item",
                modifier = Modifier.size(24.dp)
            )
            Text(text = tileName, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun CurrentMeditation(
    onClick: () -> Unit, color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {

        Column {
            Text(
                text = "Daily Thought", style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Meditation . 3-10 Min", style = MaterialTheme.typography.labelSmall
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            IconButton(onClick = { onClick() }, content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Tv Show"
                )
            }, modifier = Modifier.size(16.dp)
            )
        }
    }
}


