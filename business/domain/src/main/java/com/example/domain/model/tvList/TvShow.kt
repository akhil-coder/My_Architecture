package com.example.domain.model.tvList

data class TvShow(
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double,
)