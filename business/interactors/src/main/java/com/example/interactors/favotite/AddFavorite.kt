package com.example.interactors.favotite

import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.cache.FavoriteDbService

class AddFavorite constructor(
    private val favoriteDbService: FavoriteDbService
) {

    suspend operator fun invoke(movie: MovieDetails) {
        favoriteDbService.addFavorite(movie)
    }
}