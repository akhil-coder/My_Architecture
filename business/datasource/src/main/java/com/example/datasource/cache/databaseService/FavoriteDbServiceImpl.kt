package com.example.datasource.cache.databaseService

import com.example.datasource.cache.data.MovieDao
import com.example.datasource.cache.maper.toDbMovieDetails
import com.example.datasource.cache.maper.toDomainMovieDetails
import com.example.domain.model.movieDetails.MovieDetails
import com.example.domain.service.cache.FavoriteDbService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteDbServiceImpl @Inject constructor(
    private val movieDao: MovieDao
): FavoriteDbService {


    override fun getFavoriteMovies(): Flow<List<MovieDetails>> {
        return movieDao.getFavoriteMovies().map {
            it.map {mv ->
                mv.toDomainMovieDetails()
            }
        }
    }

    override suspend fun getFavoriteById(id: Int): MovieDetails? {
        return movieDao.getFavoriteById(id)?.toDomainMovieDetails()
    }

    override suspend fun addFavorite(movie: MovieDetails) {
        movieDao.addFavorite(movie.toDbMovieDetails())
    }

    override suspend fun updateFavorite(movie: MovieDetails) {
        movieDao.updateFavorite(movie.toDbMovieDetails())
    }

    override suspend fun deleteFavorite(movie: MovieDetails) {
        movieDao.deleteFavorite(movie.toDbMovieDetails())
    }
}