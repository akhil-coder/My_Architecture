package com.example.ui_main.screens.sdui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_main.R

object BigCard {
    @Composable
    fun Display(
        buttonTitle: String,
        description: String,
        horizontalPadding: Int,
        verticalPadding: Int,
        navigateToTvShows: (() -> Unit)
    ) {
        Column(
            Modifier
                .padding(horizontal = horizontalPadding.dp)
                .padding(vertical = verticalPadding.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigateToTvShows()
                    }, elevation = CardDefaults.elevatedCardElevation()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(horizontal = 32.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = buttonTitle,
                        style = TextStyle(fontSize = 18.sp)
                    )
                    Divider()

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_local_movies_24),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .fillMaxWidth(),
                        text = description,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}