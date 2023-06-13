package com.example.datasource.network.mapper

import com.example.datasource.network.model.tvList.TvListResponse
import com.example.domain.model.tvList.TvShow

fun TvListResponse.toDomainTvList(): List<TvShow> {
    return this.tvShowDto.map {
        TvShow(
            id = it.id,
            posterPath = appendImageBaseUrl(it.posterPath),
            originalLanguage = it.originalLanguage,
            name = it.name,
            overview = it.overview,
            voteAverage = it.voteAverage
        )
    }
}





//fun MovieDetailsResponse.toDomainMovieDetails(): MovieDetails {
//    return MovieDetails(
//        id = this.id,
//        posterPath = appendImageBaseUrl(this.posterPath),
//        originalLanguage = this.originalLanguage,
//        originalTitle = this.title,
//        overview = this.overview,
//        voteCount = this.voteCount,
//        genres = this.genres.map { it.name }
//    )
//}
