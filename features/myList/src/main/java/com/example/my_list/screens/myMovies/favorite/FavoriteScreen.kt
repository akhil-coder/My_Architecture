package com.example.my_list.screens.myMovies.favorite

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.components.DefaultScreenUI
import com.example.domain.model.movieDetails.MovieDetails
import com.example.my_list.R
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun FavoriteScreen(
    imageLoader: ImageLoader,
    state: FavoriteState,
    event: (FavoriteEvents) -> Unit
) {

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            event(FavoriteEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
        onSnackBarAction = {
            event(FavoriteEvents.RestoreFavorite)
        }
    ) {

        movieList(
            state,
            imageLoader = imageLoader,
            event
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun movieList(
    state: FavoriteState,
    imageLoader: ImageLoader,
    event: (FavoriteEvents) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){


        //items(state.movies){ movieItem ->

        itemsIndexed(
            items= state.movies,
            key={index,item->
                item.hashCode()
            }
        ){index,movieItem ->

            val delete = SwipeAction(
                icon = rememberVectorPainter(Icons.TwoTone.Delete),
                background = Color.Red,
                onSwipe = {
                    event(FavoriteEvents.RemoveFromFavorite(movieItem))
                }
            )


            SwipeableActionsBox(
                endActions = listOf(delete),
                swipeThreshold = 160.dp
            ) {
                FavoriteItem(movieItem = movieItem, imageLoader = imageLoader)
            }

            /*val state= rememberDismissState(
                confirmStateChange = {
                    if (it==DismissValue.DismissedToStart){
                        //items.remove(item)
                        event(FavoriteEvents.RemoveFromFavorite(movieItem))
                    }
                    true
                }
            )*/

            /*SwipeToDismiss(
                state = state,
                dismissThresholds ={},
                background = {
                    val color=when(state.dismissDirection){
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> Color.Red
                        null -> Color.Transparent
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint=Color.White,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }

                },
                dismissContent = {
                    FavoriteItem(movieItem = movieItem, imageLoader = imageLoader)
                },
                directions=setOf(DismissDirection.EndToStart)
            )*/




        }
    }

}

@Composable
fun FavoriteItem(movieItem: MovieDetails, imageLoader: ImageLoader) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(topEnd = 14.dp, bottomStart = 14.dp)
    ) {

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 14.dp)),
        ) {

            AsyncImage(
                model = movieItem.posterPath,
                imageLoader = imageLoader,
                contentDescription = "poster",
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(
                    id = if (isSystemInDarkTheme()) R.drawable.black_background
                    else R.drawable.white_background
                ),
                modifier = Modifier
                    .width(90.dp)
                    .height(110.dp)
            )

            Column {

                Text(
                    text = movieItem.originalTitle,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movieItem.voteCount.toString(),
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
