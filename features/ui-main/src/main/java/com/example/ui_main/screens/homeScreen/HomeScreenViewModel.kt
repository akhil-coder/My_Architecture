package com.example.ui_main.screens.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import com.example.interactors.homeUi.HomeUiInteractor
import com.example.navigation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeUiInteractor: HomeUiInteractor,
    @Named("mainLogger") private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {


    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    fun onEventChange(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = state.value.errorQueue
                    queue.remove()
                    _state.value = state.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }

        }.exhaustive
    }

}


sealed class HomeUiEvents {
    object OnRemoveHeadFromQueue : HomeUiEvents()
}