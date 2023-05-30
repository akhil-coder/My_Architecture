package com.example.datasource.cache.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.cache.model.favorite.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        val DATABASE_NAME = "Movie_Room_Database"
    }
}