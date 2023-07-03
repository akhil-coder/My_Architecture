package com.example.components

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.components.dialogs.permission.CameraPermissionTextProvider
import com.example.components.dialogs.permission.PermissionDialog
import com.example.components.dialogs.permission.PhoneCallPermissionTextProvider
import com.example.components.dialogs.permission.RecordAudioPermissionTextProvider
import com.example.components.util.ConnectivityMonitor
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.core.util.exhaustive
import com.example.navigation.network.ConnectivityObserver
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DefaultScreenUI(
    networkStatus: Boolean = true,
    appBar:@Composable () -> Unit = {},
    bottomBar:@Composable () -> Unit = {},
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    permissionQueue: Queue<String> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit = {},
    onRemovePermissionFromQueue: () -> Unit = {},
    onRecheckPermission: (String) -> Unit = {},
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    onSnackBarAction: () -> Unit = {},
    content: @Composable () -> Unit,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = appBar,
        bottomBar = bottomBar
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            Column{
                ConnectivityMonitor(isNetworkAvailable = networkStatus)
                content()
            }
            // process the queue
            if(!queue.isEmpty()){
                queue.peek()?.let { uiComponent ->
                    if(uiComponent is UIComponent.Dialog){
                        GenericDialog(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                            ,
                            title = uiComponent.title,
                            description = uiComponent.description,
                            onRemoveHeadFromQueue = onRemoveHeadFromQueue
                        )
                    } else if(uiComponent is UIComponent.SnackBar){
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = uiComponent.message,
                                actionLabel = uiComponent.action,
                                duration = SnackbarDuration.Short
                            )
                            if(result == SnackbarResult.ActionPerformed) {
                                onSnackBarAction()
                                onRemoveHeadFromQueue()
                            }
                        }
                        onRemoveHeadFromQueue()
                    }
                }
            }

            // process the permission
            if(!permissionQueue.isEmpty()) {
                permissionQueue.peek()?.let { permission ->
                    PermissionDialog(
                        permissionTextProvider = when (permission) {
                            Manifest.permission.CAMERA -> {
                                CameraPermissionTextProvider()
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                RecordAudioPermissionTextProvider()
                            }
                            Manifest.permission.CALL_PHONE -> {
                                PhoneCallPermissionTextProvider()
                            }
                            else -> return@let
                        },
                        isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                            context as Activity,
                            permission
                        ),
                        onDismiss = { onRemovePermissionFromQueue() },
                        onOkClick = {
                            onRemovePermissionFromQueue()
                            onRecheckPermission(permission)
                        },
                        onGoToAppSettingsClick = {
                            onRemovePermissionFromQueue()
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.packageName, null)
                            ).let {
                                context.startActivity(it)
                            }
                        }
                    )
                }
            }


            if(progressBarState is ProgressBarState.Loading){
                CircularIndeterminateProgressBar()
            }
        }
    }
}

@Composable
fun ShowNetworkStatus(networkState: ConnectivityObserver.Status, scaffoldState: ScaffoldState) {

}

