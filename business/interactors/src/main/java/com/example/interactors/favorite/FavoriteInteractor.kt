package com.example.interactors.favorite

data class FavoriteInteractor(
    val getFavorites: GetFavorites,
    val addFavorite: AddFavorite,
    val getFavoriteById: GetFavoriteById,
    val removeFavorite: RemoveFavorite
)
