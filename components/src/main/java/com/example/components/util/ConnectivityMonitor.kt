package com.example.components.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean,
){
    if(!isNetworkAvailable){
        Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.error)){
            Text(
                "No network connection",
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(4.dp),
                style = MaterialTheme.typography.caption,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}