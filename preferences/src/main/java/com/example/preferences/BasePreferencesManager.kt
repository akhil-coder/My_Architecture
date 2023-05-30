package com.example.preferences

import kotlinx.coroutines.flow.Flow

interface BasePreferencesManager {

    fun getTheme() : Flow<Boolean>
    suspend fun setTheme(isDarkTheme: Boolean)

    fun getAppLanguage() : Flow<String>
    suspend fun setAppLanguage(language: String)

}