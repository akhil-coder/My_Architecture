package com.example.components.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

typealias ComposableFun = @Composable ()-> Unit

data class TabViewItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val screenComponent: ComposableFun
)
