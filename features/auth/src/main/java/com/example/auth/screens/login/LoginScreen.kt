package com.example.auth.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    navigateToSignupScreen: () -> Unit,
    navigateToMovieListsScreen: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Login Screen",
                fontSize = 20.sp,
                modifier = Modifier.clickable { navigateToMovieListsScreen() }
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Signup",
                fontSize = 20.sp,
                modifier = Modifier.clickable { navigateToSignupScreen() }
            )

        }
    }
}