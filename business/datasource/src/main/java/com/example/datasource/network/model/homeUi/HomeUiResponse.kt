package com.example.datasource.network.model.homeUi

import androidx.annotation.Keep
import com.example.domain.model.homeUi.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class HomeUiResponse(
    @Expose @SerializedName("view_type") val viewType: String,
    @Expose @SerializedName("content") val content: List<Content>,
) {
    @Keep
    data class Content(
        @Expose @SerializedName("data") val `data`: Data,
        @Expose @SerializedName("view_type") val view_type: String,
        @Expose @SerializedName("vertical_padding") val vertical_padding: Int?,
        @Expose @SerializedName("horizontal_padding") val horizontal_padding: Int?,
        @Expose @SerializedName("content") val content: List<Content>? = emptyList()
    )
}