package com.example.ui_main.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ui_main.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToSignInScreen: (() -> Unit)?,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.splash_screen), fontSize = 20.sp)
    }

    LaunchedEffect(key1 = false) {
        delay(900)
        if (navigateToSignInScreen != null) {
            navigateToSignInScreen()
        }
        //navigateToMovieListsScreen()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark", showBackground = true
)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navigateToSignInScreen = null)
}