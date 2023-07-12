package com.example.datasource.cache.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_favorites")
data class FavoriteMovie(

    @PrimaryKey
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val voteCount: Int,
    /*val genres: List<String>,*/
)
