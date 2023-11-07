package com.example.preferences

import com.example.domain.model.user.SignInScreenState
import com.example.domain.model.user.SignUpScreenState
import kotlinx.coroutines.flow.Flow

interface BasePreferencesManager {

    fun getUser(): Flow<SignUpScreenState>
    suspend fun setUser(signUpState: SignInScreenState)

    fun getTheme(): Flow<Boolean>
    suspend fun setTheme(isDarkTheme: Boolean)

    fun getAppLanguage(): Flow<String>
    suspend fun setAppLanguage(language: String)

}