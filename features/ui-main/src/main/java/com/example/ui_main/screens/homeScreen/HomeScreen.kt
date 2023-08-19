package com.example.ui_main.screens.homeScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.ui_main.R
import com.example.ui_main.screens.util.ButtonBlue
import com.example.ui_main.screens.util.LightRed

@Composable
fun HomeScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: HomeUiState,
    event: (HomeUiEvents) -> Unit,
    navigateToTvShows: () -> Unit,
    openDrawer: () -> Unit
) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemTiles(
    tileName: String = "Name",
    @DrawableRes iconId: Int = R.drawable.baseline_movie_24,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .padding(10.dp)
        .defaultMinSize(minWidth = 150.dp, minHeight = 150.dp),
        elevation = 4.dp,
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
            Text(text = tileName, style = MaterialTheme.typography.h5)
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
                text = "Daily Thought", style = MaterialTheme.typography.h6
            )
            Text(
                text = "Meditation . 3-10 Min", style = MaterialTheme.typography.body1
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

