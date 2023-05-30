package com.example.datasource.network

import com.example.datasource.network.model.movieDetails.MovieDetailsResponse
import com.example.datasource.network.model.movieList.MovieListResponse
import com.example.datasource.network.tvList.TvListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    // https://api.themoviedb.org/3/search/movie?api_key=b84284a74a0341ff34f03a54cbefd84f&query=harry
    // https://api.themoviedb.org/3/movie/popular?api_key=b84284a74a0341ff34f03a54cbefd84f
    // https://api.themoviedb.org/3/movie/730629?api_key=b84284a74a0341ff34f03a54cbefd84f
    // https://developers.themoviedb.org/3/movies/get-movie-details

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") api_key: String = "b84284a74a0341ff34f03a54cbefd84f",
    ) : Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = "b84284a74a0341ff34f03a54cbefd84f",
    ) : Response<MovieDetailsResponse>

    @GET("discover/tv")
    suspend fun discoverTvShows(
        @Header("Authorization") auth_key: String = NetworkConstants.auth,
        @Query("page") page: Int = 1
    ): Response<TvListResponse>

}