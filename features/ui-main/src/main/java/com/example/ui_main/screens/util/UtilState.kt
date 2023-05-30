package com.example.ui_main.screens.util

import androidx.compose.runtime.mutableStateListOf
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent

data class UtilState(
    val permissionQueue: Queue<String> = Queue(mutableStateListOf()),
    val errorQueue: Queue<UIComponent> = Queue(mutableStateListOf())
)
