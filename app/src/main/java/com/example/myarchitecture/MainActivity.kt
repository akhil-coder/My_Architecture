package com.example.myarchitecture

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import coil.ImageLoader
import com.example.components.theme.AppTheme
import com.example.myarchitecture.navigation.NavigationProvider
import com.example.myarchitecture.network.ConnectivityManager
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
    AppTheme {
        content()
    }
}