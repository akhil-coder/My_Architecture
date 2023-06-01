package com.example.datasource.di

import android.content.Context
import androidx.room.Room
import com.example.datasource.cache.data.MovieDataBase
import com.example.datasource.network.WebService
import com.example.datasource.network.networkService.MovieNetworkServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideWebService() =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WebService::class.java)



    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) : MovieDataBase =
        Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            MovieDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(movieDatabase: MovieDataBase) = movieDatabase.movieDao()

}