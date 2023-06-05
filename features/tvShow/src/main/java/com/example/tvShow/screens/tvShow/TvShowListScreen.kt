package com.example.tvShow.screens.tvShow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.components.DefaultScreenUI
import com.example.domain.model.tvList.TvShow

@Composable
fun TvShowListScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: TvShowListState,
    event: (TvShowListEvents) -> Unit,
    navigateToDetailsScreen: ((String) -> Unit)? = null,
    savedStateHandle: SavedStateHandle?
) {
    DefaultScreenUI(
        networkStatus = networkStatus.value, queue = state.errorQueue, onRemoveHeadFromQueue = {
            event(TvShowListEvents.OnRemoveHeadFromQueue)
        }, progressBarState = state.progressBarState
    ) {
        TvShowList(
            state = state,
            imageLoader = imageLoader,
            navigateToDetailsScreen = navigateToDetailsScreen,
            event = event
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvShowList(
    state: TvShowListState,
    imageLoader: ImageLoader,
    navigateToDetailsScreen: ((String) -> Unit)? = null,
    event: (TvShowListEvents) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(state.tvShows) { item ->
            tvShowListItem(item)
        }
    }
}

@Preview(device = "id:pixel_6_pro")
@Composable
fun tvShowListItem(
    item: TvShow = TvShow(
        id = 123,
        name = "English",
        originalLanguage = "ieoj",
        overview = "The best",
        posterPath = "the/def/efd",
        voteAverage = 2023.00
    )
) {
    // TODO: Random Generated Gradient Colors
    val gradientColorList = listOf(
        listOf(Color(0xFFFF0000), Color(0xFFDF0909), Color(0xFF9B0505)),
        listOf(Color(0xFFFFEB3B), Color(0xFFDFAA09), Color(0xFFF13B0E)),
        listOf(Color(0xFF009688), Color(0xFF19CCBB), Color(0xFFCDDBDA)),
        listOf(Color(0xFF3547AC), Color(0xFF3F51B5), Color(0xFF0C9C8F)),
        listOf(Color(0xFF3547AC), Color(0xFF00BCD4), Color(0xFF27E4FC))
    )

    Card(
        modifier = Modifier
            .padding(2.dp)
            .clickable {},
        elevation = 8.dp,
        shape = RoundedCornerShape(topEnd = 14.dp, bottomStart = 14.dp)
    ) {
        Column(
            modifier = Modifier.background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true, colors = gradientColorList.random()
                )
            ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(item.posterPath)
                    .crossfade(true).build(),
                contentDescription = "poster",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(200.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.originalLanguage,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.overview,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun GradientBackgroundBrush(
    isVerticalGradient: Boolean, colors: List<Color>
): Brush {
    val endOffset = if (isVerticalGradient) {
        Offset(0F, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors, start = Offset.Zero, end = endOffset
    )
}
