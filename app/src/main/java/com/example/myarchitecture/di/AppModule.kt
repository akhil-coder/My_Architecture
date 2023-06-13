package com.example.myarchitecture.di

import android.content.Context
import coil.ImageLoader
import coil.memory.MemoryCache
import com.example.auth.navigation.AuthNavigation
import com.example.movie.navigation.MovieNavigation
import com.example.my_list.navigation.MyListNavigation
import com.example.myarchitecture.AppApplication
import com.example.myarchitecture.R
import com.example.myarchitecture.navigation.NavigationProvider
import com.example.profile.navigation.ProfileNavigation
import com.example.tvShow.navigation.TvShowScreenNavigation
import com.example.ui_main.navigation.UiMainNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AppApplication {
        return app as AppApplication
    }

    @Singleton
    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }
            .crossfade(true)
            .build()
    }


    @Provides
    fun provideNavigationProvider(
        uiMainNavigation: UiMainNavigation,
        tvShowScreenNavigation: TvShowScreenNavigation,
        authNavigation: AuthNavigation,
        movieNavigation: MovieNavigation,
        profileNavigation: ProfileNavigation,
        myListNavigation: MyListNavigation
    ): NavigationProvider {
        return NavigationProvider(
            uiMainNavigation = uiMainNavigation,
            tvShowScreenNavigation = tvShowScreenNavigation,
            authNavigation = authNavigation,
            movieNavigation = movieNavigation,
            profileNavigation = profileNavigation,
            myListNavigation = myListNavigation
        )
    }
}