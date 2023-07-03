package com.example.datasource.network.networkService

import com.example.core.domain.DataState
import com.example.core.domain.UIComponent
import com.example.datasource.network.ServerUiService
import com.example.datasource.network.WebService
import com.example.datasource.network.mapper.toDomainHomeUi
import com.example.domain.model.homeUi.HomeUi
import com.example.domain.service.network.HomeUiNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeUiNetworkServiceImpl @Inject constructor(
    private val serverUiService: ServerUiService
) : HomeUiNetworkService {

    override suspend fun getHomeUi(): Flow<DataState<HomeUi>> = flow {
        try {
            val response = serverUiService.getHomeUi()
            if (response.isSuccessful) {
                emit(DataState.Data(response.body()!!.toDomainHomeUi()))
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