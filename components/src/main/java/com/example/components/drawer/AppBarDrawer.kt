package com.example.components.drawer

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun AppBarDrawer(
    title: String,
    onNavigationItemClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        /*backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.surface,*/
        navigationIcon = {
            IconButton(onClick = onNavigationItemClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer",
                    tint = Color.White
                )
            }
        }
    )
}