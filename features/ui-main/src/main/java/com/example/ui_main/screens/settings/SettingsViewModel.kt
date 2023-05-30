package com.example.ui_main.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preferences.BasePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val basePreferencesManager: BasePreferencesManager
) : ViewModel() {

    private val _themeState = mutableStateOf(false)
    val themeState: State<Boolean> = _themeState

    private val _languageState = mutableStateOf("en")
    val languageState: State<String> = _languageState

    var isLanguageDialogShone by mutableStateOf(false)
        private set


    init {
        basePreferencesManager.getTheme().onEach {
            _themeState.value = it
        }.launchIn(viewModelScope)

        basePreferencesManager.getAppLanguage().onEach {
            _languageState.value = it
        }.launchIn(viewModelScope)
    }


    fun setTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            basePreferencesManager.setTheme(isDarkTheme)
        }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch {
            basePreferencesManager.setAppLanguage(language)
        }
    }

    fun showLanguageDialog() {
        isLanguageDialogShone = true
    }

    fun dismissLanguageDialog() {
        isLanguageDialogShone = false
    }
}