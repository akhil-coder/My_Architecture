package com.example.tvShow.screens.tvShowDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.components.AppDrawer
import com.example.domain.model.tvList.TvShow
import com.example.navigation.screens.TvShowScreen
import com.example.tvShow.R
import com.example.tvShow.screens.tvShow.TvShowListState
import kotlinx.coroutines.launch

@Composable
fun TvShowDetailsScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    state: TvShowListState,
) {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: TvShowScreen.TvShowDetails.route

    val coroutineScope = rememberCoroutineScope()

    state.selectedTvShow?.let {
        CoverAndProfileImage(
            tvShow = state.selectedTvShow!!, imageLoader = imageLoader
        )
    }
}

@Composable
fun TvShowDetails(
    tvShow: TvShow, imageLoader: ImageLoader
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(tvShow.posterPath)
                .crossfade(true).build(),
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
                .layoutId("posterPic")
        )

        Card(
            modifier = Modifier
                .padding(2.dp)
                .layoutId("detailCard"),
            elevation = 8.dp,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = tvShow.name,
                        style = MaterialTheme.typography.h3.copy(fontFamily = FontFamily.SansSerif)
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.HeartBroken,
                            contentDescription = "Toggle drawer",
                            tint = Color.Red
                        )
                    }
                }

                Text(
                    text = tvShow.originalLanguage, style = MaterialTheme.typography.h5
                )

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        imageVector = Icons.Default.RateReview,
                        contentDescription = "Toggle drawer",
                        tint = Color.Red
                    )
                    Text(
                        text = tvShow.voteAverage.toString(), style = MaterialTheme.typography.h6
                    )
                }

                Divider(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    thickness = 2.dp,
                    color = Color(0xFFB3AAAA)
                )

                Text(
                    text = stringResource(R.string.description), style = MaterialTheme.typography.h5
                )

                Text(
                    text = tvShow.overview.toString(), style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun OverlappingBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier.verticalScroll(rememberScrollState()),
        content = content,
    ) { measurables, constraints ->
        val largeBox = measurables[0]
        val smallBox = measurables[1]
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )

        val largePlaceable = largeBox.measure(looseConstraints)
        val smallPlaceable = smallBox.measure(looseConstraints)
        layout(
            width = constraints.maxWidth,
            height = (largePlaceable.height * 0.95 + smallPlaceable.height).toInt(),
        ) {
            largePlaceable.placeRelative(
                x = 0,
                y = 0,
            )

            smallPlaceable.placeRelative(
                x = 0, y = (largePlaceable.height * 0.95).toInt()
            )
        }
    }
}

@Composable
fun CoverAndProfileImage(
    modifier: Modifier = Modifier,
    tvShow: TvShow,
    imageLoader: ImageLoader,
    onCoverClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    var thumbIconLiked by remember {
        mutableStateOf(true)
    }
    OverlappingBoxes(modifier = modifier.fillMaxSize()) {

        Box(modifier = Modifier.clickable { onCoverClick() }) {
            ImageItem(
                imageLoader = imageLoader,
                modifier = Modifier.fillMaxSize(),
                data = tvShow.posterPath ?: R.drawable.black_background,
                contentScale = ContentScale.Crop
            )
        }

        Box(modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onProfileClick() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .layoutId("detailCard"),
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = tvShow.name,
                            style = MaterialTheme.typography.h3.copy(fontFamily = FontFamily.SansSerif),
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { thumbIconLiked = !thumbIconLiked }) {
                            Icon(
                                imageVector = if (thumbIconLiked) Icons.Outlined.MonitorHeart else Icons.Outlined.HeartBroken,
                                contentDescription = "Toggle drawer",
                                tint = Color.Red
                            )
                        }
                    }

                    Text(
                        text = tvShow.originalLanguage, style = MaterialTheme.typography.h5
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            imageVector = Icons.Default.RateReview,
                            contentDescription = "Toggle drawer",
                            tint = Color.Red
                        )
                        Text(
                            text = tvShow.voteAverage.toString(),
                            style = MaterialTheme.typography.h6
                        )
                    }

                    Divider(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                        thickness = 2.dp,
                        color = Color(0xFFB3AAAA)
                    )

                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = tvShow.overview.toString(), style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier,
    data: Any?,
    crossFadeValue: Int = 300,
    contentDescription: String? = null,
    contentScale: ContentScale,
    imageLoader: ImageLoader
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(data).crossfade(false).build(),
        contentDescription = "DetailImage",
        imageLoader = imageLoader,
        contentScale = contentScale,
        placeholder = painterResource(
            id = if (isSystemInDarkTheme()) R.drawable.black_background
            else R.drawable.white_background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6F)
    )
}

@Composable
fun FlowRow(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier, measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints)
            }
            layout(
                width = constraints.maxWidth, height = constraints.maxHeight
            ) {
                var xPosition = 0
                placeables.forEach { placeable ->
                    placeable.place(
                        x = xPosition, y = 0
                    )
                    xPosition += placeable.width
                }
            }
        }, content = content
    )
}