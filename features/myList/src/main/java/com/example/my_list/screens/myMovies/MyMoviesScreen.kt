package com.example.my_list.screens.myMovies

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import com.example.components.tab.TabContent
import com.example.components.tab.TabViewItem
import com.example.components.tab.Tabs
import com.example.my_list.R
import com.example.my_list.screens.myMovies.favorite.FavoriteEvents
import com.example.my_list.screens.myMovies.favorite.FavoriteScreen
import com.example.my_list.screens.myMovies.favorite.FavoriteState
import com.example.my_list.screens.myMovies.watchedList.WatchListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyMoviesScreen(
    popBack: () -> Unit,
    imageLoader: ImageLoader,
    stateFav: FavoriteState,
    eventFav: (FavoriteEvents) -> Unit,
    openDrawer: () -> Unit
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val tabList = listOf(
        TabViewItem(id = "favorite",
            title = stringResource(R.string.favorite),
            icon = Icons.Default.Favorite,
            screenComponent = {
                FavoriteScreen(
                    imageLoader = imageLoader,
                    state = stateFav,
                    event = eventFav,
                    openDrawer = openDrawer,
                )
            }), TabViewItem(id = "watchedList",
            title = stringResource(R.string.watched),
            icon = Icons.Default.Tv,
            screenComponent = { WatchListScreen() })
    )

    BackHandler(pagerState.currentPage != 0) {
        scope.launch {
            pagerState.animateScrollToPage(0)
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(R.string.my_movies), color = Color.White)
        }, navigationIcon = {
            IconButton(onClick = popBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back pressed",
                    tint = Color.White
                )
            }
        })
    }) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Tabs(tabs = tabList, pagerState = pagerState)
            TabContent(tabs = tabList, pagerState = pagerState)

        }

    }
}