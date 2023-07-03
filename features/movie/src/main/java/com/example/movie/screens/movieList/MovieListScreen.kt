package com.example.movie.screens.movieList

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.components.ComposableLifecycle
import com.example.components.DefaultScreenUI

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListScreen(
    imageLoader: ImageLoader,
    networkStatus : MutableState<Boolean>,
    state: MovieListState,
    event: (MovieListEvents) -> Unit,
    savedStateHandle: SavedStateHandle?,
    navigateToDetailsScreen: (String) -> Unit,
) {

    if (savedStateHandle != null) {
        val resultData by savedStateHandle.getLiveData<String>("resultData").observeAsState()
        LaunchedEffect(resultData) {
            resultData?.let {
                Log.e("COMPOSE_RESULT::", "resultData: $resultData")
                savedStateHandle.remove<String>("resultData")
            }
        }
    }
    
    ComposableLifecycle{ source, event ->
        when(event){
            Lifecycle.Event.ON_CREATE->{ Log.e("Lifecycle::", "MovieListScreen: onCreate")}
            Lifecycle.Event.ON_START->{Log.e("Lifecycle::", "MovieListScreen: ON_START")}
            Lifecycle.Event.ON_RESUME->{Log.e("Lifecycle::", "MovieListScreen: ON_RESUME")}
            Lifecycle.Event.ON_PAUSE->{Log.e("Lifecycle::", "MovieListScreen: ON_PAUSE")}
            Lifecycle.Event.ON_STOP->{Log.e("Lifecycle::", "MovieListScreen: ON_STOP")}
            Lifecycle.Event.ON_DESTROY->{Log.e("Lifecycle::", "MovieListScreen: ON_DESTROY")}
            else -> {Log.e("Lifecycle::", "MovieListScreen: Else")}
        }
    }
    
    DefaultScreenUI(
        networkStatus = networkStatus.value,
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(MovieListEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState
    ) {

        MovieList(
            state,
            imageLoader = imageLoader,
            navigateToDetailsScreen,
            event
        )

        /*Scaffold(
            topBar =  {
                TopAppBar(
                    title = {
                        Text(text = "Home")
                    },
                    elevation = 4.dp
                )
            },
        ) {
            movieList(
                state,
                imageLoader = imageLoader,
                navigateToDetailsScreen,
                event
            )
        }*/
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    state: MovieListState,
    imageLoader: ImageLoader,
    navigateToDetailsScreen: (String) -> Unit,
    event: (MovieListEvents) -> Unit
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(state.movies) { movieItem ->

            Card(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable {
                        navigateToDetailsScreen(movieItem.id.toString())
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(topEnd = 14.dp, bottomStart = 14.dp)

            ) {

                Column() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movieItem.posterPath)
                            .crossfade(true)
                            .build(),
                        contentDescription = "poster",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = movieItem.title,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = movieItem.voteAverage.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                        Icon(
                            imageVector = if(movieItem.voteAverage >= 6) Icons.Default.ThumbUp else Icons.Default.ThumbDown,
                            contentDescription = "Rating",
                            tint = Color.Blue,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = movieItem.originalLanguage,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 12.dp)
                    )

                }

            }
        }
    }
}
