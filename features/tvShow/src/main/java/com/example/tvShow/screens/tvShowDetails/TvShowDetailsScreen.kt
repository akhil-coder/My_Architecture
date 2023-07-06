package com.example.tvShow.screens.tvShowDetails

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tvShow.R

@Composable
fun TvShowDetailsScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: TvShowDetailsState,
    event: (TvShowDetailsEvents) -> Unit,
    navigateToDetailsScreen: ((String) -> Unit)? = null,
    savedStateHandle: SavedStateHandle?,
) {
    TvShowDetails(state, imageLoader)
}

@Composable
fun TvShowDetails(
    state: TvShowDetailsState,
    imageLoader: ImageLoader,
) {
    state.tvShowDetails.let {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(it?.posterPath)
                    .crossfade(true),
                contentDescription = "DetailImage",
                imageLoader = imageLoader,
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(
                    id = if (isSystemInDarkTheme()) R.drawable.black_background
                    else R.drawable.white_background
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }

}