package com.example.core.domain

sealed class UIComponent{

    data class Dialog(
        val title: String,
        val description: String
    ): UIComponent()

    data class None(
        val message: String
    ): UIComponent()

    data class SnackBar(
        val message: String,
        val action: String
    ): UIComponent()

}
