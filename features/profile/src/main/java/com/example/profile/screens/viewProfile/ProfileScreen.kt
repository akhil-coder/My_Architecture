package com.example.profile.screens.viewProfile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.example.components.DefaultScreenUI
import com.example.components.image.CircleProfileImage
import com.example.profile.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    imageLoader: ImageLoader,
    networkStatus: MutableState<Boolean>,
    navigateToProfileEditsScreen: () -> Unit,
    openDrawer: () -> Unit,
) {

    var menuExpanded = remember {
        mutableStateOf(false)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri })

    DefaultScreenUI(
        networkStatus = networkStatus.value,
        appBar = { CustomAppBar(menuExpanded) },
        openDrawer = openDrawer,
        content = {

            Column(modifier = Modifier.padding(12.dp)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box() {
                        CircleProfileImage(
                            imageLoader = imageLoader, uri = selectedImageUri
                        )
                        IconButton(modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp),
                            onClick = {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit profile pic"
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.padding(start = 12.dp),
                    ) {
                        Text(
                            text = "Name", style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "name@email.com", style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    thickness = 2.dp,
                )

            }

        },
        drawerEnable = false
    )
    if (menuExpanded.value) ShowPopUpMenuDialog(menuExpanded, navigateToProfileEditsScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(menuExpanded: MutableState<Boolean>) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.profile), color = Color.White)
        },
        actions = {
            IconButton(onClick = {
                menuExpanded.value = !menuExpanded.value
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more",
                    tint = Color.White,
                )
            }
        },
    )
}

@Composable
fun ShowPopUpMenuDialog(
    menuExpanded: MutableState<Boolean>, navigateToProfileEditsScreen: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(true)
    }

    val items = listOf<String>("Edit")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
                menuExpanded.value = false
            }, modifier = Modifier.width(140.dp)
        ) {

            /*  items.forEachIndexed { index, itemText ->
                  DropdownMenuItem(onClick = {
                      expanded = false
                      menuExpanded.value = false
                      when (itemText) {
                          "Edit" -> navigateToProfileEditsScreen()
                      }
                  }) {

                      Icon(
                          imageVector = when (itemText) {
                              "Edit" -> Icons.Default.Edit
                              else -> Icons.Default.Edit
                          }, contentDescription = "Icons", tint = Color.LightGray
                      )

                      Text(
                          text = itemText, modifier = Modifier.padding(start = 4.dp)
                      )
                  }
              }*/

        }

    }
}
