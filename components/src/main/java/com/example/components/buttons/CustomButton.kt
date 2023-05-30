package com.example.components.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled : Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(text = text, color = Color.White)
    }

}