package com.example.ui_main.screens.sdui.layoutcontainers

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.ui_main.screens.sdui.SDUIDisplayManager

object VerticalScrollable {
    @Composable
    fun Display(data:List<Any>) {
        Column(content = {
               data.map { SDUIDisplayManager.Display(data = it) }
        })

    }
}