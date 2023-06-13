package com.example.domain.model.homeUi

data class HomeUi(
    val view_type: String,
    val content: List<Content>?
) {
    data class Content(
        val data: Data,
        val view_type: String,
        val vertical_padding: Int?,
        val horizontal_padding: Int?,
        val content: List<Content>? = null
    )
}