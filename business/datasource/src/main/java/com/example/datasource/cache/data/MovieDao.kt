package com.example.datasource.cache.data

import androidx.room.*
import com.example.datasource.cache.model.favorite.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_favorites")
    fun getFavoriteMovies(): Flow<List<FavoriteMovie>>

    @Query("SELECT * FROM tbl_favorites WHERE id= :id")
    suspend fun getFavoriteById(id: Int) : FavoriteMovie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(movie : FavoriteMovie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(movie : FavoriteMovie)

    @Delete
    suspend fun deleteFavorite(movie : FavoriteMovie)
}