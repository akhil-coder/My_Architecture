package com.example.datasource.network.mapper

import com.example.datasource.network.model.movieDetails.MovieDetailsResponse
import com.example.datasource.network.model.movieList.MovieListResponse
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.model.movieList.Movie

fun MovieListResponse.toDomainMovieList(): List<Movie> {
    return this.results.map {
        Movie(
            id = it.id,
            posterPath = appendImageBaseUrl(it.posterPath),
            originalLanguage = it.originalLanguage,
            title = it.title,
            voteAverage = it.voteAverage
        )
    }
}

fun MovieDetailsResponse.toDomainMovieDetails(): MovieDetails {
    return MovieDetails(
        id = this.id,
        posterPath = this.posterPath?.let { appendImageBaseUrl(it) },
        originalLanguage = this.originalLanguage,
        originalTitle = this.title,
        overview = this.overview,
        voteCount = this.voteCount,
        genres = this.genres.map { it.name }
    )
}

fun appendImageBaseUrl(url: String?): String? = url.let { "https://image.tmdb.org/t/p/w500$it" } ?: run { "" }