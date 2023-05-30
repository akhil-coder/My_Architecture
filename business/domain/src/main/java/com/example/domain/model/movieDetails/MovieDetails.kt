package com.example.domain.model.movieDetails

data class MovieDetails(
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val voteCount: Int,
    val genres: List<String>,
)
