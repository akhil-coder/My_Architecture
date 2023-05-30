package com.example.interactors.favotite

data class FavoriteInteractor(
    val getFavorites: GetFavorites,
    val addFavorite: AddFavorite,
    val getFavoriteById: GetFavoriteById,
    val removeFavorite: RemoveFavorite
)
