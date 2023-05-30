package com.example.components.image

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.components.R

@Composable
fun CircleProfileImage(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    uri: Uri? = null
) {
    Surface(
        modifier = modifier
            .size(80.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = 4.dp,
        //color = Color.Black.copy(alpha = 0.5f)//MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(130.dp)
                .padding(5.dp),
            contentScale = ContentScale.Fit,

            )*/

        AsyncImage(
            model = uri,
            imageLoader = imageLoader,
            contentDescription = "poster",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(
                id =  R.drawable.ic_person
            ),
            error = painterResource(
                id =  R.drawable.ic_person
            ),
            modifier = Modifier
                .size(130.dp)
                .padding(5.dp),
        )
    }
}