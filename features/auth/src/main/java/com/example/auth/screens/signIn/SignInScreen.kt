package  com.example.auth.screens.signIn

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
import com.example.components.text.ClickableLoginTextComponent
import com.example.components.text.DividerTextComponent
import com.example.components.text.HeadingTestComponent
import com.example.components.text.MyTextFieldComponent
import com.example.components.text.NormalTestComponent
import com.example.components.text.PasswordTextFieldComponent
import com.example.components.text.UnderLinedTestComponent
import com.example.components.theme.AppTheme
import com.example.domain.model.user.SignInScreenState

@Composable
fun SignInScreen(
    state: SignInScreenState?,
    events: ((SignInUIEvents) -> Unit)?,
    navigateToMovieListsScreen: (() -> Unit)?,
    navigateToSignUpScreen: (() -> Unit)?,
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            ) {
            NormalTestComponent(value = stringResource(R.string.signIn))

            HeadingTestComponent(value = stringResource(R.string.welcome_back))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(
                    id = R.drawable.baseline_alternate_email_24
                ),
                onValueChange = { newValue -> events?.invoke(SignInUIEvents.UpdateEmail(newValue)) },
                isError = state?.isBadEmail,
                errorMessage = stringResource(
                    id = R.string.invalid_email_format
                )
            )

            PasswordTextFieldComponent(
                value = state?.password,
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.baseline_password_24),
                onValueChange = { newValue -> events?.invoke(SignInUIEvents.UpdatePassword(newValue)) },
                isError = state?.isBadPassword,
                errorMessage = stringResource(id = R.string.invalid_password),
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTestComponent(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(260.dp))

            ButtonComponent(
                value = stringResource(id = R.string.signIn),
                navigateToMovieListsScreen,
                isEnabled = state!!.isBadData   // TODO: Handle Validation on Click
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = false, onTextSelected = {}, onClick = navigateToSignUpScreen
            )

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight", showBackground = true
)
@Composable
fun LoginScreenPreview() {
    SignInScreen(
        state = SignInScreenState(
            email = "Akhil@gmail.com",
            password = "123456567",
            isBadEmail = false,
            isBadPassword = false
        ), events = null, navigateToMovieListsScreen = null, navigateToSignUpScreen = null
    )
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark", showBackground = true
)
@Composable
fun LoginScreenPreviewDark() {
    AppTheme(true) {
        SignInScreen(
            state = SignInScreenState(
                email = "Akhil@gmail.com",
                password = "123456567",
                isBadEmail = false,
                isBadPassword = false
            ), events = null, navigateToMovieListsScreen = null, navigateToSignUpScreen = null
        )
    }

}


