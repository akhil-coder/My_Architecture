package com.example.components.text

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.R
import com.example.components.componentShapes

@Composable
fun NormalTestComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 18.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTestComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal
        ), textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onValueChange: (String) -> Unit,
    isError: Boolean?,
    errorMessage: String
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .clip(componentShapes.extraSmall),
        label = {
            if (isError == true) Text(text = errorMessage) else Text(text = labelValue)
        },
        value = textValue.value,
        isError = isError!!,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            textValue.value = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onValueChange: (String) -> Unit,
    isError: Boolean?,
    errorMessage: String,
    value: String?
) {
    val password = remember {
        mutableStateOf("")
    }

    password.value = value!!

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.extraSmall),

        label = {
            if (isError == true) Text(text = errorMessage) else Text(text = labelValue)
        },
        value = password.value,
        isError = isError!!,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        onValueChange = {
            password.value = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
    )
}

@Composable
fun CheckBoxComponent(value: String, onTextSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value = !checkedState.value
        })

        ClickableTextComponent(value = value, onTextSelected = onTextSelected)

    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept out "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Terms of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)

        withStyle(style = SpanStyle(color = Color.Gray)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }

        append(andText)

        withStyle(style = SpanStyle(color = Color.Gray)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = privacyPolicyText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("Clickable Text", "ClickableTextComponent: {${span.item}}")

            if (span.item == termsAndConditionsText || span.item == privacyPolicyText) {
                onTextSelected(span.item)
            }
        }

    })
}

@Composable
fun ButtonComponent(value: String, onClick: (() -> Unit)?, isEnabled: Boolean) {
    Button(
        onClick = { onClick },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.filledTonalButtonColors(),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 14.sp,
            color = Color.Black
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(
    tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit, onClick: (() -> Unit)?
) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)

        withStyle(style = SpanStyle(color = Color.Gray)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp), style = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center
    ), text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("Clickable Text", "ClickableTextComponent: {${span.item}}")

            if (span.item == loginText) {
                onTextSelected(span.item)
                onClick?.let { it() }
            }
        }
    })
}

@Composable
fun UnderLinedTestComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 16.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline
    )
}
