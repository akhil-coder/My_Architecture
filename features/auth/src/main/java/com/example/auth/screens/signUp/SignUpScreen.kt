package com.example.auth.screens.signUp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.R
import com.example.components.text.ButtonComponent
import com.example.components.text.CheckBoxComponent
import com.example.components.text.ClickableLoginTextComponent
import com.example.components.text.DividerTextComponent
import com.example.components.text.HeadingTestComponent
import com.example.components.text.MyTextFieldComponent
import com.example.components.text.NormalTestComponent
import com.example.components.text.PasswordTextFieldComponent
import com.example.components.theme.AppTheme

@Composable
fun SignUpScreen(
    state: SignUpScreenState?,
    events: ((SignUpUIEvents) -> Unit)?,
    navigateToSignUpScreen: (() -> Unit)?,
) {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
        ) {
            NormalTestComponent(value = stringResource(R.string.hello))

            HeadingTestComponent(value = stringResource(R.string.create_an_account))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(R.string.first_name),
                painterResource = painterResource(id = R.drawable.baseline_co_present_24),
                onValueChange = { newValue -> events?.invoke(SignUpUIEvents.UpdateFirstName(newValue)) },
                isError = state?.isBadFirstName,
                errorMessage = stringResource(R.string.invalid_first_name)
            )

            MyTextFieldComponent(
                labelValue = stringResource(R.string.last_name),
                painterResource = painterResource(id = R.drawable.baseline_co_present_24),
                onValueChange = { newValue -> events?.invoke(SignUpUIEvents.UpdateLastName(newValue)) },
                isError = state?.isBadLastName,
                errorMessage = stringResource(R.string.invalid_last_name)
            )

            MyTextFieldComponent(
                labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.baseline_alternate_email_24),
                onValueChange = { newValue -> events?.invoke(SignUpUIEvents.UpdateEmail(newValue)) },
                isError = state?.isBadEmail,
                errorMessage = stringResource(id = R.string.invalid_email_format)
            )

            MyTextFieldComponent(
                labelValue = stringResource(R.string.password),
                painterResource = painterResource(id = R.drawable.baseline_password_24),
                onValueChange = { newValue -> events?.invoke(SignUpUIEvents.UpdatePassword(newValue)) },
                isError = state?.isBadPassword,
                errorMessage = stringResource(id = R.string.invalid_password)
            )

            PasswordTextFieldComponent(
                value = state?.passwordConfirm,
                labelValue = stringResource(R.string.confirm_password),
                painterResource = painterResource(id = R.drawable.baseline_password_24),
                onValueChange = { newValue ->
                    events?.invoke(
                        SignUpUIEvents.UpdatePasswordConfirm(
                            newValue
                        )
                    )
                },
                isError = state?.isBadPasswordConfirm,
                errorMessage = stringResource(id = R.string.invalid_password)
            )

            CheckBoxComponent(value = stringResource(R.string.by_continuing_you), onTextSelected = {

            })

            Spacer(modifier = Modifier.height(80.dp))

            ButtonComponent(
                value = stringResource(R.string.register),
                onClick = { events?.invoke(SignUpUIEvents.SignUp) },
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = true, onTextSelected = {}, onClick = navigateToSignUpScreen
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight", showBackground = true
)
@Composable
fun DefaultPreviewSignUpScreenLight() {
    AppTheme(useDarkTheme = false) {
        SignUpScreen(
            state = SignUpScreenState(
                firstName = "Akhil",
                lastName = "S",
                email = "akhil@gmail.com",
                password = "1234As%343",
                passwordConfirm = "1234As",
                checkBox = false,
                isBadFirstName = false,
                isBadLastName = false,
                isBadEmail = false,
                isBadPassword = false,
                isBadPasswordConfirm = false,
            ), events = null, navigateToSignUpScreen = null
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark", showBackground = true
)
@Composable
fun DefaultPreviewSignUpScreenDark() {
    AppTheme(useDarkTheme = true) {
        SignUpScreen(
            state = SignUpScreenState(
                firstName = "Akhil",
                lastName = "S",
                email = "akhil@gmail.com",
                password = "1234As%343",
                passwordConfirm = "1234As",
                checkBox = false,
                isBadFirstName = false,
                isBadLastName = false,
                isBadEmail = false,
                isBadPassword = false,
                isBadPasswordConfirm = false,
            ), events = null, navigateToSignUpScreen = null
        )
    }
}