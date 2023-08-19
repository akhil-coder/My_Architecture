package com.example.auth.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.textState.ConfirmPasswordState
import com.example.components.textState.EmailState
import com.example.components.textState.EmailStateSaver
import com.example.components.textState.PasswordState
import com.example.components.textState.TextFieldState
import com.example.core.domain.UIComponentState

@Composable
fun LoginScreen(
    state: LoginUiState, events: (LoginUIEvents) -> Unit
) {
    LoginFirminiq(state, events)
}

@Composable
fun LoginFirminiq(state: LoginUiState, events: (LoginUIEvents) -> Unit) {

    val passwordState = remember { PasswordState() }

    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(null))
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.30f), Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = com.example.components.R.drawable.ic_shape),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp, vertical = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = com.example.components.R.drawable.ic_flower),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .weight(1f)
                            .size(100.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(text = "Welcome", fontSize = 20.sp, color = Color.White)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
            ) {
                val focusRequester = remember { FocusRequester() }


                Text(text = "Log in", style = MaterialTheme.typography.h1)
                Spacer(modifier = Modifier.height(8.dp))
                Email(emailState, onImeAction = { focusRequester.requestFocus() })

                Password(label = "Password",
                    passwordState = passwordState,
                    modifier = Modifier.focusRequester(focusRequester),
                    onImeAction = { })

                val confirmPasswordState =
                    remember { ConfirmPasswordState(passwordState = passwordState) }

                Password(label = "Confirm Password",
                    passwordState = confirmPasswordState,
                    modifier = Modifier.focusRequester(focusRequester),
                    onImeAction = { })

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        events(LoginUIEvents.UpdateWelcomeDialogState(UIComponentState.Show))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    enabled = emailState.isValid && passwordState.isValid && confirmPasswordState.isValid
                ) {
                    Text(text = "Log in")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (state.welcomeDialogState is UIComponentState.Show) {
                    WelcomeDialog(onCloseDialog = { events(LoginUIEvents.UpdateWelcomeDialogState(UIComponentState.Hide)) })
                }
            }
        }
    }
}

@Composable
fun WelcomeDialog(onCloseDialog: () -> Unit) {
    AlertDialog(onDismissRequest = { onCloseDialog() }, title = {
        Text(
            text = "Welcome User",
            style = MaterialTheme.typography.h4,
        )
    }, confirmButton = {
        TextButton(onClick = { onCloseDialog() }) {
            Text(text = "Ok")
        }
    })
}

@Composable
fun Email(
    emailState: TextFieldState = remember { EmailState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = emailState.text,
        onValueChange = {
            emailState.text = it
        },
        label = {
            Text(
                text = "Email",
                style = MaterialTheme.typography.body2,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                emailState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.body2,
        isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction, keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        }),
    )

    emailState.getError()?.let { error -> TextFieldError(textError = error) }
}

@Composable
fun Password(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
            passwordState.enableShowErrors()
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.body2,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
            )
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility, contentDescription = "Hide Password"
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "Show Password"
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = passwordState.showErrors(),

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction, keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        }),
    )
    passwordState.getError()?.let { error -> TextFieldError(textError = error) }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError, modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.error
        )
    }
}