package com.example.movie.screens.movieDetails


import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.components.DefaultScreenUI
import com.example.movie.R

@Composable
fun MovieDetailsScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: MovieDetailsState,
    event: (MovieDetailsEvents) -> Unit,
    backWithResult: (Map<String, Any>?) -> Unit,
    openDrawer: () -> Unit
) {

    DefaultScreenUI(
        networkStatus = networkStatus.value,
        appBar = { CustomAppBar(state, event, backWithResult) },
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(MovieDetailsEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
        content = {
            movieDetail(
                state,
                imageLoader = imageLoader,
                event
            )
        },
        openDrawer = { openDrawer() }
    )
}

@Composable
fun CustomAppBar(
    state: MovieDetailsState,
    event: (MovieDetailsEvents) -> Unit,
    backWithResult: (Map<String, Any>?) -> Unit
) {

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.movie_Details), color = Color.White)
        },
        actions = {
            IconButton(
                onClick = {
                    if (state.isFavorite) {
                        event(MovieDetailsEvents.RemoveFromFavorite)
                    } else {
                        event(MovieDetailsEvents.AddToFavorite)
                    }
                }
            ) {
                Icon(
                    imageVector = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Red.copy(alpha = 0.6f)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                backWithResult(
                    mapOf(
                        "resultData" to "my result data"
                    )
                )
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back pressed",
                    tint = Color.White
                )
            }
        },
        elevation = 4.dp
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun movieDetail(
    state: MovieDetailsState,
    imageLoader: ImageLoader,
    event: (MovieDetailsEvents) -> Unit
) {


    state.movieDetails?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = "poster",
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 14.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = it.originalTitle,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = it.genres.toString(),
                        style = MaterialTheme.typography.caption,
                        lineHeight = 24.sp,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.voteCount.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Rating",
                            tint = Color.Blue,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = it.overview,
                        style = MaterialTheme.typography.body1,
                        lineHeight = 24.sp,
                        fontSize = 18.sp
                    )

                }
            }

        }
    }

}
