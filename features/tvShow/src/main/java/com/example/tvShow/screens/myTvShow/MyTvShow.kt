package com.example.tvShow.screens.myTvShow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import coil.ImageLoader

@Composable
fun MyTvShow(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>
) {
    Text(text = "My Tv Show", modifier = Modifier.fillMaxSize())

}