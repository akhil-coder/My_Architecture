package com.example.ui_main.screens.sdui.layoutcontainers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui_main.screens.sdui.SDUIDisplayManager

object HorizontalScrollable {

    @Composable
    fun Display(data: List<Any>) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            item {
                data.map { SDUIDisplayManager.Display(data = it) }
            }
        }

    }
}