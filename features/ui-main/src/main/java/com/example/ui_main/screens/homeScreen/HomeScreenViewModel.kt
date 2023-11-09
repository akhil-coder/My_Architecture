package com.example.ui_main.screens.homeScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.util.Logger
import com.example.interactors.homeUi.HomeUiInteractor
import com.example.navigation.network.ConnectivityObserver
import javax.inject.Inject
import javax.inject.Named

class HomeScreenViewModel @Inject constructor(
    private val homeUiInteractor: HomeUiInteractor,
    @Named("mainLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {


}