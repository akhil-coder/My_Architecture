package com.example.myarchitecture

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.network.ConnectivityObserver
import com.example.preferences.BasePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainAppViewModel @Inject constructor(
 private val basePreferencesManager: BasePreferencesManager,
 private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _themeState = mutableStateOf(false)
    val themeState: State<Boolean> = _themeState

    private val _networkState = mutableStateOf(ConnectivityObserver.Status.Idle)
    val networkState: State<ConnectivityObserver.Status> = _networkState


    init {
        basePreferencesManager.getTheme().onEach {
            _themeState.value = it
        }.launchIn(viewModelScope)

        connectivityObserver.observe().onEach {

            if(_networkState.value == ConnectivityObserver.Status.Idle && it == ConnectivityObserver.Status.Available) {
                _networkState.value = ConnectivityObserver.Status.Idle
            } else {
                _networkState.value = it
            }
        }.launchIn(viewModelScope)
    }
}