package com.medify.app.presentation.login

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medify.app.R
import com.medify.app.designsystem.component.LoadingDialog
import com.medify.app.designsystem.component.MedifyInputField
import com.medify.app.designsystem.theme.MedifyTheme
import com.medify.app.helper.PopupErrorDialog
import com.medify.app.helper.isValidEmail
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), onRegisterNowClick: () -> Unit, onLoginSuccess: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val activity = LocalActivity.current

    LaunchedEffect(uiState.loginToken) {
        if (uiState.loginToken.isNotBlank()) {
            onLoginSuccess()
        }
    }

    LoginContent(
        uiState = uiState,
        onEmailChange = { email ->
            viewModel.onEvent(LoginEvent.OnEmailValueChange(email))
        },
        onPasswordChange = { password ->
            viewModel.onEvent(LoginEvent.OnPasswordValueChange(password))
        },
        onPasswordVisibilityChange = {
            viewModel.onEvent(LoginEvent.OnPasswordVisibilityChange(!uiState.isPasswordVisible))
        },
        onLoginClick = {
            viewModel.onEvent(LoginEvent.OnLoginClick(email = uiState.email, password = uiState.password))
        },
        onDismissPopupErrorDialog = {
            viewModel.onEvent(LoginEvent.OnDismissPopupErrorDialog)
        },
        onForgotPasswordClick = {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.feature_not_available),
                Toast.LENGTH_SHORT
            ).show()
        },
        onRegisterNowClick = onRegisterNowClick
    )
}

@Composable
private fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onLoginClick: () -> Unit,
    onDismissPopupErrorDialog: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterNowClick: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            val (
                welcomeGreetingText,
                welcomeTitleText,
                welcomeSubtitleText,
                authMedicalImage,
                emailTitleText,
                emailInputField,
                passwordTitleText,
                forgotPasswordTitleText,
                passwordInputField,
                loginButton,
                noAccountTitleText,
                registerNowTitleText,
                copyRightText,
            ) = createRefs()

            if (uiState.isLoginLoading) {
                LoadingDialog(properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false))
            }

            if (uiState.loginError != null) {
                PopupErrorDialog(
                    throwable = uiState.loginError,
                    onButtonClick = onDismissPopupErrorDialog,
                    onDismissRequest = onDismissPopupErrorDialog
                )
            }

            createHorizontalChain(welcomeGreetingText, welcomeTitleText, chainStyle = ChainStyle.Packed)

            Text(
                modifier = Modifier
                    .constrainAs(welcomeGreetingText) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(welcomeTitleText.start)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 0F
                    }
                    .padding(top = 90.dp),
                text = stringResource(R.string.greeting_hello),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                modifier = Modifier
                    .constrainAs(welcomeTitleText) {
                        start.linkTo(welcomeGreetingText.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 0F
                    }
                    .padding(start = 4.dp, top = 90.dp),
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                modifier = Modifier
                    .constrainAs(welcomeSubtitleText) {
                        start.linkTo(parent.start)
                        top.linkTo(welcomeGreetingText.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 0F
                    }
                    .padding(top = 4.dp),
                text = stringResource(R.string.login_prompt),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            Image(
                modifier = Modifier
                    .constrainAs(authMedicalImage) {
                        end.linkTo(parent.end)
                        top.linkTo(welcomeSubtitleText.bottom)
                    }
                    .offset(x = 20.dp),
                painter = painterResource(R.drawable.img_auth_medical_illustration),
                contentDescription = null
            )

            Text(
                modifier = Modifier.constrainAs(emailTitleText) {
                    start.linkTo(parent.start)
                    top.linkTo(authMedicalImage.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                    horizontalBias = 0F
                },
                text = stringResource(R.string.email_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .constrainAs(emailInputField) {
                        start.linkTo(parent.start)
                        top.linkTo(emailTitleText.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 8.dp),
                value = uiState.email,
                placeholder = stringResource(R.string.email_placeholder),
                errorMessage = if (uiState.email.isBlank() || uiState.email.isValidEmail()) "" else stringResource(R.string.error_email_invalid),
                onValueChange = onEmailChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                trailingIcon = {
                    if (uiState.email.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp),
                            onClick = {
                                onEmailChange("")
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                )
                            }
                        )
                    }
                }
            )

            createHorizontalChain(passwordTitleText, forgotPasswordTitleText, chainStyle = ChainStyle.SpreadInside)

            Text(
                modifier = Modifier.constrainAs(passwordTitleText) {
                    start.linkTo(parent.start)
                    top.linkTo(emailInputField.bottom, margin = 40.dp)
                    end.linkTo(forgotPasswordTitleText.start, margin = 8.dp)
                    width = Dimension.preferredWrapContent
                    horizontalBias = 0F
                },
                text = stringResource(R.string.password_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier
                    .constrainAs(forgotPasswordTitleText) {
                        start.linkTo(passwordTitleText.end, margin = 8.dp)
                        top.linkTo(passwordTitleText.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(passwordTitleText.bottom)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 1F
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onForgotPasswordClick
                    ),
                text = stringResource(R.string.forgot_password),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                ),
                color = MaterialTheme.colorScheme.primaryContainer
            )

            MedifyInputField(
                modifier = Modifier
                    .constrainAs(passwordInputField) {
                        start.linkTo(parent.start)
                        top.linkTo(passwordTitleText.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 8.dp),
                value = uiState.password,
                placeholder = stringResource(R.string.password_placeholder),
                errorMessage = if (uiState.password.isBlank() || uiState.password.length >= 8) "" else stringResource(R.string.error_password_min_length),
                onValueChange = onPasswordChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                        if (uiState.password.isNotEmpty()) {
                            IconButton(
                                modifier = Modifier.size(20.dp),
                                onClick = {
                                    onPasswordChange("")
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = null,
                                    )
                                }
                            )
                        }

                        Spacer(Modifier.width(8.dp))

                        IconButton(
                            modifier = Modifier.size(20.dp),
                            onClick = onPasswordVisibilityChange,
                            content = {
                                Icon(
                                    imageVector = if (uiState.isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                    contentDescription = null,
                                )
                            }
                        )
                    }
                }
            )

            Button(
                modifier = Modifier.constrainAs(loginButton) {
                    start.linkTo(parent.start)
                    top.linkTo(passwordInputField.bottom, margin = 40.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                enabled = (uiState.email.isNotBlank() && uiState.email.isValidEmail()) && (uiState.password.isNotBlank() && uiState.password.length >= 8),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues.Zero,
                onClick = onLoginClick,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(R.string.login),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
            )

            createHorizontalChain(noAccountTitleText, registerNowTitleText, chainStyle = ChainStyle.Packed)

            Text(
                modifier = Modifier.constrainAs(noAccountTitleText) {
                    start.linkTo(parent.start)
                    top.linkTo(loginButton.bottom, margin = 30.dp)
                    end.linkTo(registerNowTitleText.start)
                    bottom.linkTo(copyRightText.top, margin = 30.dp)
                    width = Dimension.preferredWrapContent
                    verticalBias = 0F
                },
                text = stringResource(R.string.no_account),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                modifier = Modifier
                    .constrainAs(registerNowTitleText) {
                        start.linkTo(noAccountTitleText.end)
                        top.linkTo(noAccountTitleText.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(noAccountTitleText.bottom)
                        width = Dimension.preferredWrapContent
                    }
                    .padding(start = 4.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onRegisterNowClick
                    ),
                text = stringResource(R.string.register_now),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                modifier = Modifier.constrainAs(copyRightText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    verticalBias = 1F
                },
                text = stringResource(R.string.copyright_label),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    MedifyTheme {
        LoginContent(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordVisibilityChange = {},
            onLoginClick = {},
            onDismissPopupErrorDialog = {},
            onForgotPasswordClick = {},
            onRegisterNowClick = {}
        )
    }
}
