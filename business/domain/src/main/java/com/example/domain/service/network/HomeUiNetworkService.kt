package com.example.domain.service.network

import com.example.core.domain.DataState
import com.example.domain.model.homeUi.HomeUi
import kotlinx.coroutines.flow.Flow

interface HomeUiNetworkService {
    suspend fun getHomeUi() : Flow<DataState<HomeUi>>
}