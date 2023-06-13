package com.example.ui_main.screens.sdui

import androidx.compose.runtime.Composable
import com.example.domain.model.homeUi.HomeUi
import com.example.ui_main.screens.sdui.components.BigCard
import com.example.ui_main.screens.sdui.components.ButtonCard
import com.example.ui_main.screens.sdui.layoutcontainers.HorizontalScrollable
import com.example.ui_main.screens.sdui.layoutcontainers.VerticalScrollable

object SDUIDisplayManager {
    @Composable
    fun Display(data: Any) {
        when (data) {
            is HomeUi.Content -> {
                Display(key = data.view_type, data)
            }

        }
    }

    @Composable
    fun Display(key: String, data: Any) = when (key) {
        "buttonCard" -> {
            val dataContent = data as HomeUi.Content
            val title = dataContent.data.title ?: ""
            ButtonCard.Display(
                title,
                dataContent.horizontal_padding ?: 0,
                dataContent.vertical_padding ?: 0
            )
        }
        "bigCard" -> {
            val dataContent = data as HomeUi.Content
            val title = dataContent.data.title ?: ""
            val description = dataContent.data.description ?: ""
            BigCard.Display(
                title,
                description,
                dataContent.horizontal_padding ?: 0,
                dataContent.vertical_padding ?: 0
            )
        }
        "row" -> {
            val dataContent = data as HomeUi.Content
            dataContent.content?.let {
                HorizontalScrollable.Display(it)
            }
        }
        "column" -> {
            val dataContent = data as HomeUi.Content
            dataContent.content?.let {
                VerticalScrollable.Display(it)
            }
        }

        else -> Unit
    }

    @Composable
    fun RootDisplay(data: List<Any>) {
        HorizontalScrollable.Display(data)
        VerticalScrollable.Display(data)
    }

    @Composable
    fun RootDisplay(response: HomeUi) {
        when (response.view_type) {
            "column" -> {
                response.content?.let { VerticalScrollable.Display(it) }
            }

            "row" -> {
                response.content?.let { HorizontalScrollable.Display(it) }
            }
        }
        val list = response.content


    }

}