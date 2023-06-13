package com.example.datasource.network.mapper

import com.example.datasource.network.model.homeUi.HomeUiResponse
import com.example.domain.model.homeUi.HomeUi

fun HomeUiResponse.toDomainHomeUi(): HomeUi {
    return HomeUi(view_type = this.viewType, content = this.content.map {
        it.toDomainContent()
    })
}

fun HomeUiResponse.Content.toDomainContent(): HomeUi.Content {

    return HomeUi.Content(
        content = this.content?.map {
            it.toDomainContent()
        },
        view_type = this.view_type,
        vertical_padding = this.vertical_padding,
        horizontal_padding = this.horizontal_padding,
        data = this.data,
    )
}