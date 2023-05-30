package com.example.navigation.di

import android.content.Context
import com.example.navigation.network.ConnectivityObserver
import com.example.navigation.network.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {

    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context) : ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}