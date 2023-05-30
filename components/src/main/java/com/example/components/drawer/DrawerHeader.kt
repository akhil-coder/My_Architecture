package com.example.components.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.example.components.image.CircleProfileImage

@Composable
fun DrawerHeader(imageLoader: ImageLoader) {

    Column(
        modifier = Modifier.padding(top = 16.dp)
            .fillMaxWidth(0.8f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircleProfileImage(
            modifier = Modifier.size(60.dp),
            imageLoader = imageLoader
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Name",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "name@email.com",
            style = MaterialTheme.typography.caption
        )
        
        Divider(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
            thickness = 2.dp,
        )

    }

}