package com.example.interactors.homeUi

import com.example.core.domain.DataState
import com.example.core.domain.ProgressBarState
import com.example.core.domain.UIComponent
import com.example.core.util.exhaustive
import com.example.domain.model.homeUi.HomeUi
import com.example.domain.service.network.HomeUiNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHomeUi constructor(
    private val networkService: HomeUiNetworkService
) {

    operator fun invoke(): Flow<DataState<HomeUi>> = flow {

        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            networkService.getHomeUi().collect {
                when (it) {
                    is DataState.Data -> emit(DataState.Data(it.data))
                    is DataState.Loading -> emit(DataState.Loading(it.progressBarState))
                    is DataState.Response -> emit(DataState.Response(it.uiComponent))
                }.exhaustive
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error", description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}