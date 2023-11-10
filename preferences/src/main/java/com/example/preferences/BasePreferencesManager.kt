package com.example.preferences

import kotlinx.coroutines.flow.Flow

interface BasePreferencesManager {

//    fun getUser(): Flow<com.example.auth.screens.signUp.SignUpScreenState>
//    suspend fun setUser(signUpState: com.example.auth.screens.signIn.SignInScreenState)

    fun getTheme(): Flow<Boolean>
    suspend fun setTheme(isDarkTheme: Boolean)

    fun getAppLanguage(): Flow<String>
    suspend fun setAppLanguage(language: String)

}