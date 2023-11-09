package com.example.ui_main.screens.util

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.components.DefaultScreenUI
import com.example.components.buttons.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UtilScreen(
    state: UtilState, event: (UtilEvents) -> Unit, popBack: () -> Unit, openDrawer: () -> Unit
) {

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val openCamera = remember { mutableStateOf<Boolean>(false) }

    val permissionsToRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
    )

    val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            var granded = true
            permissionsToRequest.forEach { permission ->
                Log.e("PERM::", "$permission: ${perms[permission] == true}")
                if (perms[permission] != true) granded = false
                event(
                    UtilEvents.AddPermission(
                        permission = permission, isGranted = perms[permission] == true
                    )
                )
            }
            if (granded) {
                openCamera.value = true
            }
        })

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        bitmap.value = it
    }

    DefaultScreenUI(queue = state.errorQueue,
        permissionQueue = state.permissionQueue,
        onRemoveHeadFromQueue = {
            event(UtilEvents.OnRemoveHeadFromQueue)
        },
        onRemovePermissionFromQueue = {
            event(UtilEvents.OnRemovePermission)
        },
        onRecheckPermission = { permission ->
            multiplePermissionResultLauncher.launch(
                arrayOf(permission)
            )
        },
        content = {
/*
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = "Utils Screen", color = Color.White)
                    }, navigationIcon = {
                        IconButton(onClick = { popBack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back pressed",
                                tint = Color.White
                            )
                        }
                    })
                },

                ) {
                SetContent(

                    state,
                    event,
                    permissionsToRequest,
                    multiplePermissionResultLauncher,
                    bitmap.value
                )
                if (openCamera.value) {
                    launcher.launch()
                    openCamera.value = false
                }
            }
*/
        },
        openDrawer = { openDrawer() })
}


@Composable
fun SetContent(
    state: UtilState,
    event: (UtilEvents) -> Unit,
    permissionsToRequest: Array<String>,
    multiplePermissionResultLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    bitmap: Bitmap?
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        CustomButton(text = "Multiple Permission", modifier = Modifier.padding(vertical = 12.dp)) {
            multiplePermissionResultLauncher.launch(permissionsToRequest)
        }

        Spacer(modifier = Modifier.height(20.dp))

        bitmap?.let {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }

    }
}
