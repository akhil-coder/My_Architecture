package com.example.datasource.cache.maper

import com.example.datasource.cache.model.favorite.FavoriteMovie
import com.example.domain.model.movieDetails.MovieDetails


fun MovieDetails.toDbMovieDetails(): FavoriteMovie {
    return FavoriteMovie(
        id = this.id,
        posterPath = this.posterPath,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        voteCount = this.voteCount,
        /*genres = this.genres.map { it.name }*/
    )

}

fun FavoriteMovie.toDomainMovieDetails(): MovieDetails {
    return MovieDetails(
        id = this.id,
        posterPath = this.posterPath,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        voteCount = this.voteCount,
        genres = emptyList()
    )

}