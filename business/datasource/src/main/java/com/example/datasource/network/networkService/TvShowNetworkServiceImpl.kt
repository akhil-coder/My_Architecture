package com.example.datasource.network.networkService

import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.datasource.network.WebService
import com.example.datasource.network.mapper.toDomainTvList
import com.example.domain.model.tvList.TvShow
import com.example.domain.service.network.TvShowNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowNetworkServiceImpl @Inject constructor(
    private val webService: WebService
) : TvShowNetworkService {

    override suspend fun discoverTvShows(): Flow<DataState<List<TvShow>>> = flow {
        try {
            val response = webService.discoverTvShows()
            if (response.isSuccessful) {
                emit(DataState.Data(response.body()!!.toDomainTvList()))
            } else {
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Failed",
                            description = "Could not connect with server. Try again later"
                        )
                    )
                )
            }

        } catch (e: Exception) {
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error", description = e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }
}