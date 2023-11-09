package com.example.ui_main.screens.sdui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


object ButtonCard {
    @Composable
    fun Display(buttonTitle: String, horizontalPadding: Int, verticalPadding: Int) {
        Column(
            Modifier
                .padding(horizontal = horizontalPadding.dp)
                .padding(vertical = verticalPadding.dp)
        ) {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .clickable {

                    },
                elevation = CardDefaults.elevatedCardElevation(),

                ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = com.example.ui_main.R.drawable.baseline_local_movies_24),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = buttonTitle, style = TextStyle(fontWeight = FontWeight.Bold))
                }
            }
        }
    }

    data class ButtonCardData(val title: String)
}