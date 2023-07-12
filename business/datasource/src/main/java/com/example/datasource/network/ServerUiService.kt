package com.example.datasource.network

import com.example.datasource.network.model.homeUi.HomeUiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServerUiService {

    @GET("main_paige")
    suspend fun getHomeUi(): Response<HomeUiResponse>
}