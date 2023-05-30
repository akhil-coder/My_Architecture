package com.example.ui_main.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ui_main.R
import com.example.ui_main.screens.util.ButtonBlue
import com.example.ui_main.screens.util.DeepBlue
import com.example.ui_main.screens.util.LightRed

@Composable
fun HomeScreen(navigateToTvShows: () -> Unit) {

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            CurrentMeditation(navigateToTvShows)
        }
    }
}

@Composable
fun CurrentMeditation(
    onClick: () -> Unit, color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {

        Column {
            Text(
                text = "Daily Thought", style = MaterialTheme.typography.h6
            )
            Text(
                text = "Meditation . 3-10 Min", style = MaterialTheme.typography.body1
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            IconButton(onClick = { onClick() }, content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Tv Show"
                )
            }, modifier = Modifier.size(16.dp)
            )
        }

    }
}
