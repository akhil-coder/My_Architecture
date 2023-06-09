package com.example.interactors.favorite

import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.cache.FavoriteDbService

class RemoveFavorite constructor(
    private val favoriteDbService: FavoriteDbService
) {

    suspend operator fun invoke(movie: MovieDetails) {
        favoriteDbService.deleteFavorite(movie)
    }
}