package com.example.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.model.user.SignUpScreenState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val DataStore_Name = "base_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DataStore_Name
)

class BasePreferencesManagerImpl constructor(
    private val context: Context
) : BasePreferencesManager {

    companion object {

        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val APP_LANGUAGE = stringPreferencesKey("app_language")
        val USER_DETAILS_USERNAME = stringPreferencesKey("user_details_user_name")
        val USER_DETAILS_PASSWORD = stringPreferencesKey("user_details_password")

    }

    override fun getUser() = context.dataStore.data.catch { exception ->
        if (exception is IOException) emit(emptyPreferences())
        else throw exception
    }.map { preferences ->
        val userName = preferences[USER_DETAILS_USERNAME] ?: ""
        val password = preferences[USER_DETAILS_PASSWORD] ?: ""
        SignUpScreenState(email = userName, password = password)
    }

    override suspend fun setUser(signUpState: SignUpScreenState) {
        context.dataStore.edit { preferences ->
            preferences[USER_DETAILS_USERNAME] = signUpState.email!!
            preferences[USER_DETAILS_PASSWORD] = signUpState.password!!
        }
    }

    override fun getTheme() = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[IS_DARK_THEME] ?: false
    }

    override suspend fun setTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_THEME] = isDarkTheme
        }
    }

    override fun getAppLanguage() = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[APP_LANGUAGE] ?: "en"
    }

    override suspend fun setAppLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[APP_LANGUAGE] = language
        }
    }
}