package com.example.domain.model.movieList

data class Movie(
    val id: Int,
    val originalLanguage: String,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double,
)
