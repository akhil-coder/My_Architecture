package com.example.interactors.favorite
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.cache.FavoriteDbService


class GetFavoriteById constructor(
    private val favoriteDbService: FavoriteDbService
) {

    suspend operator fun invoke(id: Int) : MovieDetails? {
        return favoriteDbService.getFavoriteById(id)
    }
}