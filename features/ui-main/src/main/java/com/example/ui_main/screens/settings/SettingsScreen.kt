package com.example.ui_main.screens.settings

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.LocaleListCompat
import com.example.components.buttons.CustomButton
import com.example.core.util.getKey
import com.example.ui_main.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    appLanguage: String,
    isLanguageDialogShone: Boolean,
    popBack: () -> Unit,
    setTheme: (Boolean) -> Unit,
    setLanguage: (String) -> Unit,
    showLanguageDialog: () -> Unit,
    dismissLanguageDialog: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings), color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back pressed",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(R.string.dark_theme),
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                }

                CustomButton(
                    text = stringResource(R.string.language),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    showLanguageDialog()
                }
            }

            if (isLanguageDialogShone) {
                LanguageDialog(
                    appLanguage,
                    onConfirm = {
                        setLanguage(it)
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(
                                it
                            )
                        )
                    },
                    onDismiss = {
                        dismissLanguageDialog()
                    }
                )
            }
        }

    }

}

@Composable
fun LanguageDialog(
    appLanguage: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {

    val languages = HashMap<String, String>()
    languages["en"] = "English"
    languages["hi"] = "Hindi"

    var selectedLanguage by remember {
        mutableStateOf(appLanguage)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            //usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {

        Card(
            elevation = CardDefaults.elevatedCardElevation(),
            shape = RoundedCornerShape(16.dp),
            // modifier = Modifier.fillMaxWidth(0.95f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text(
                    text = stringResource(R.string.choose_language),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Column {
                    languages.values.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == languages[selectedLanguage]),
                                    onClick = {
                                        selectedLanguage = languages.getKey(text)
                                    }
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == languages[selectedLanguage]),
                                onClick = {
                                    selectedLanguage = languages.getKey(text)
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary,
                                )
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium.merge(),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomButton(text = stringResource(R.string.cancel)) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    CustomButton(text = stringResource(R.string.ok)) {
                        onConfirm(selectedLanguage)
                        onDismiss()
                    }
                }
            }

        }

    }

}