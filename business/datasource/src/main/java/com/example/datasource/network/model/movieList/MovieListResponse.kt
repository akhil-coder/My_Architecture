package com.example.datasource.network.model.movieList


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MovieListResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<MovieDao>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)