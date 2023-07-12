package com.example.datasource.network.model.tvList


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class TvListResponse(
    @Expose @SerializedName("page") val page: Int,
    @Expose @SerializedName("results") val tvShowDto: List<TvShowDto>,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("total_results") val totalResults: Int
) {
    @Keep
    data class TvShowDto(
        @Expose @SerializedName("backdrop_path") val backdropPath: String,
        @Expose @SerializedName("first_air_date") val firstAirDate: String,
        @Expose @SerializedName("genre_ids") val genreIds: List<Int>,
        @Expose @SerializedName("id") val id: Int,
        @Expose @SerializedName("name") val name: String,
        @Expose @SerializedName("origin_country") val originCountry: List<String>,
        @Expose @SerializedName("original_language") val originalLanguage: String,
        @Expose @SerializedName("original_name") val originalName: String,
        @Expose @SerializedName("overview") val overview: String,
        @Expose @SerializedName("popularity") val popularity: Double,
        @Expose @SerializedName("poster_path") val posterPath: String,
        @Expose @SerializedName("vote_average") val voteAverage: Double,
        @Expose @SerializedName("vote_count") val voteCount: Int
    )
}