package com.example.myarchitecture

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.myarchitecture.navigation.NavigationProvider
import com.example.myarchitecture.network.ConnectivityManager
import com.example.myarchitecture.ui.theme.Black1
import com.example.myarchitecture.ui.theme.MyArchitectureTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private val viewModel: MainAppViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

            if (widthSizeClass == WindowWidthSizeClass.Compact) {
                this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            } else
                this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER


            AppContent(viewModel) {
                val navController = rememberAnimatedNavController()//rememberNavController()
                MainScreen(
                    widthSizeClass = widthSizeClass,
                    navController = navController,
                    imageLoader = imageLoader,
                    navigationProvider = navigationProvider,
                    networkStatus = connectivityManager.isNetworkAvailable
                )
            }
        }
    }
}

@Composable
fun AppContent(viewModel: MainAppViewModel, content: @Composable () -> Unit) {
    MyArchitectureTheme(darkTheme = viewModel.themeState.value) {
        //window.statusBarColor=MaterialTheme.colors.primaryVariant.toArgb()

        val systemUiController = rememberSystemUiController()
        val color = if (viewModel.themeState.value) Black1 else MaterialTheme.colors.primary
        SideEffect {
            systemUiController.setStatusBarColor(
                color = color,
                darkIcons = false,
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}