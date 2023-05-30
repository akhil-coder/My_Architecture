package com.example.components

import com.example.navigation.network.ConnectivityObserver

sealed class UiEvent{
    data class ShowSnackBar(val state: ConnectivityObserver.Status) : UiEvent()
}
