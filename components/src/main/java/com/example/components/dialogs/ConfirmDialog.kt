package com.example.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.components.buttons.CustomButton
import com.example.components.R
import com.example.components.buttons.ButtonOutlined

@Composable
fun ConfirmDialog(
    isDialogVisible: Boolean,
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onNegativeButtonClicked: () -> Unit,
    onDismissed: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismissed,
        properties = DialogProperties(
            //usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {

        Card(
            elevation = 8.dp,
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
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )


                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp, horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    ButtonOutlined(text = negativeButtonText) {
                        onNegativeButtonClicked()
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    CustomButton(text = positiveButtonText) {
                        onPositiveButtonClicked()
                    }
                }
            }

        }

    }

}