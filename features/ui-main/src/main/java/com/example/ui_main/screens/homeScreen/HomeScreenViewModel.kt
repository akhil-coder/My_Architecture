package com.example.ui_main.screens.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.interactors.homeUi.HomeUiInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeUiInteractor: HomeUiInteractor,
    @Named("mainLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _homeUiState = mutableStateOf(HomeUiState())
    val homeUiState: State<HomeUiState> = _homeUiState

    init {
        onEventChange(event = HomeUiEvents.GetHomeUi)
    }

    fun onEventChange(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetHomeUi -> getHomeUi()
            is HomeUiEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = homeUiState.value.errorQueue
                    queue.remove()
                    _homeUiState.value = homeUiState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }

        }.exhaustive
    }

    private fun getHomeUi() {
        homeUiInteractor.getHomeUi().onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    _homeUiState.value = homeUiState.value.copy(
                        homeUi = dataState.data
                    )
                    logger.log("home detail : ${dataState.data?.view_type}")
                }

                is DataState.Loading -> {
                    _homeUiState.value = homeUiState.value.copy(
                        progressBarState = dataState.progressBarState
                    )
                }

                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            addToMessageQueue(dataState.uiComponent)
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }

                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }

                        is UIComponent.SnackBar -> {

                        }
                    }.exhaustive
                }
            }.exhaustive
        }.launchIn(viewModelScope)
    }

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = homeUiState.value.errorQueue
        queue.add(uiComponent)
        _homeUiState.value = homeUiState.value.copy(
            errorQueue = queue
        )
    }
}

sealed class HomeUiEvents {
    object GetHomeUi : HomeUiEvents()
    object OnRemoveHeadFromQueue : HomeUiEvents()
}