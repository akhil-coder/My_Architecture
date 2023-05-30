package com.example.preferences.di

import android.content.Context
import com.example.preferences.BasePreferencesManager
import com.example.preferences.BasePreferencesManagerImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun provideBasePreferencesManager(@ApplicationContext context: Context) : BasePreferencesManager {
        return BasePreferencesManagerImpl(context)
    }
}