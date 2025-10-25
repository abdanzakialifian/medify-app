package com.medify.app.presentation.registration

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medify.app.R
import com.medify.app.designsystem.component.MedifyInputField
import com.medify.app.designsystem.theme.MedifyTheme
import com.medify.app.helper.isValidEmail
import com.medify.app.helper.isValidIndonesianPhone
import com.medify.app.helper.normalizePhone
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(viewModel: RegisterViewModel = koinViewModel(), onLoginNowClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val activity = LocalActivity.current

    RegistrationContent(
        uiState = uiState,
        onFirstNameChange = { firstName ->
            viewModel.onEvent(RegisterEvent.OnFirstNameValueChange(firstName))
        },
        onLastNameNameChange = { lastName ->
            viewModel.onEvent(RegisterEvent.OnLastNameValueChange(lastName))
        },
        onIdNumberChange = { idNumber ->
            viewModel.onEvent(RegisterEvent.OnIdNumberValueChane(idNumber))
        },
        onEmailChange = { email ->
            viewModel.onEvent(RegisterEvent.OnEmailValueChange(email))
        },
        onPhoneNoChange = { phoneNo ->
            viewModel.onEvent(RegisterEvent.OnPhoneNoValueChange(phoneNo))
        },
        onPasswordChange = { password ->
            viewModel.onEvent(RegisterEvent.OnPasswordValueChange(password))
        },
        onConfirmPasswordChange = { confirmPassword ->
            viewModel.onEvent(RegisterEvent.OnConfirmPasswordValueChange(confirmPassword))
        },
        onPasswordVisibilityChange = {
            viewModel.onEvent(RegisterEvent.OnPasswordVisibilityChange(!uiState.isPasswordVisible))
        },
        onRegisterClick = {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.feature_not_available),
                Toast.LENGTH_SHORT
            ).show()
        },
        onLoginNowClick = onLoginNowClick
    )
}

@Composable
private fun RegistrationContent(
    uiState: RegisterUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameNameChange: (String) -> Unit,
    onIdNumberChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNoChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginNowClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (
            idNumberTitleText,
            idNumberInputField,
            emailTitleText,
            emailInputField,
            phoneNoTitleText,
            phoneNoInputField,
            passwordTitleText,
            passwordInputField,
            confirmPasswordTitleText,
            confirmPasswordInputField,
        ) = createRefs()

        val (
            welcomeGreetingText,
            welcomeTitleText,
            welcomeSubtitleText,
            authMedicalImage,
            nameLayout,
            loginButton,
            noAccountTitleText,
            registerNowTitleText,
            copyRightText,
        ) = createRefs()

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
            text = stringResource(R.string.register_prompt),
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(nameLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(authMedicalImage.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.first_name_label),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )

                MedifyInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = uiState.firstName,
                    placeholder = stringResource(R.string.first_name_placeholder),
                    onValueChange = onFirstNameChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Done),
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1F)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.last_name_label),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )

                MedifyInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = uiState.lastName,
                    placeholder = stringResource(R.string.last_name_placeholder),
                    onValueChange = onLastNameNameChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Done),
                )
            }
        }

        Text(
            modifier = Modifier.constrainAs(idNumberTitleText) {
                start.linkTo(parent.start)
                top.linkTo(nameLayout.bottom, margin = 40.dp)
                end.linkTo(parent.end)
                width = Dimension.preferredWrapContent
                horizontalBias = 0F
            },
            text = stringResource(R.string.id_number_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
        )

        MedifyInputField(
            modifier = Modifier
                .constrainAs(idNumberInputField) {
                    start.linkTo(parent.start)
                    top.linkTo(idNumberTitleText.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 8.dp),
            value = uiState.idNumber,
            placeholder = stringResource(R.string.id_number_placeholder),
            errorMessage = if (uiState.idNumber.isBlank() || uiState.idNumber.length >= 16) "" else stringResource(R.string.error_nik_length),
            onValueChange = { idNumber ->
                if (idNumber.length <= 16) {
                    onIdNumberChange(idNumber)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        )

        Text(
            modifier = Modifier.constrainAs(emailTitleText) {
                start.linkTo(parent.start)
                top.linkTo(idNumberInputField.bottom, margin = 40.dp)
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
        )

        Text(
            modifier = Modifier.constrainAs(phoneNoTitleText) {
                start.linkTo(parent.start)
                top.linkTo(emailInputField.bottom, margin = 40.dp)
                end.linkTo(parent.end)
                width = Dimension.preferredWrapContent
                horizontalBias = 0F
            },
            text = stringResource(R.string.phone_number_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
        )

        MedifyInputField(
            modifier = Modifier
                .constrainAs(phoneNoInputField) {
                    start.linkTo(parent.start)
                    top.linkTo(phoneNoTitleText.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 8.dp),
            value = uiState.phoneNo.normalizePhone(),
            placeholder = stringResource(R.string.phone_number_placeholder),
            errorMessage = if (uiState.phoneNo.isBlank() || uiState.phoneNo.isValidIndonesianPhone()) "" else stringResource(R.string.error_phone_invalid),
            onValueChange = { phoneNo ->
                if (phoneNo.length <= 13) {
                    onPhoneNoChange(phoneNo)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
        )

        Text(
            modifier = Modifier.constrainAs(passwordTitleText) {
                start.linkTo(parent.start)
                top.linkTo(phoneNoInputField.bottom, margin = 40.dp)
                end.linkTo(parent.end)
                width = Dimension.preferredWrapContent
                horizontalBias = 0F
            },
            text = stringResource(R.string.password_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
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
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp),
                    onClick = onPasswordVisibilityChange,
                    content = {
                        Icon(
                            imageVector = if (uiState.isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                        )
                    }
                )
            }
        )

        Text(
            modifier = Modifier.constrainAs(confirmPasswordTitleText) {
                start.linkTo(parent.start)
                top.linkTo(passwordInputField.bottom, margin = 40.dp)
                end.linkTo(parent.end)
                width = Dimension.preferredWrapContent
                horizontalBias = 0F
            },
            text = stringResource(R.string.confirm_password_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
        )

        MedifyInputField(
            modifier = Modifier
                .constrainAs(confirmPasswordInputField) {
                    start.linkTo(parent.start)
                    top.linkTo(confirmPasswordTitleText.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 8.dp),
            value = uiState.confirmPassword,
            placeholder = stringResource(R.string.confirm_password_placeholder),
            errorMessage = when {
                uiState.confirmPassword.isNotBlank() && uiState.confirmPassword.length < 8 -> stringResource(R.string.error_password_min_length)
                uiState.confirmPassword.isNotBlank() && uiState.confirmPassword != uiState.password -> stringResource(R.string.error_password_not_match)
                else -> ""
            },
            onValueChange = onConfirmPasswordChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp),
                    onClick = onPasswordVisibilityChange,
                    content = {
                        Icon(
                            imageVector = if (uiState.isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                        )
                    }
                )
            }
        )

        Button(
            modifier = Modifier.constrainAs(loginButton) {
                start.linkTo(parent.start)
                top.linkTo(confirmPasswordInputField.bottom, margin = 40.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            enabled = (uiState.firstName.isNotBlank() && uiState.lastName.isNotBlank()) &&
                    (uiState.idNumber.isNotBlank() && uiState.idNumber.length == 16) &&
                    (uiState.email.isNotBlank() && uiState.email.isValidEmail()) &&
                    (uiState.phoneNo.isNotBlank() && uiState.phoneNo.isValidIndonesianPhone()) &&
                    (uiState.password.isNotBlank() && uiState.password.length >= 8) &&
                    (uiState.confirmPassword.isNotBlank() && uiState.confirmPassword.length >= 8 && uiState.confirmPassword == uiState.password),
            shape = RoundedCornerShape(8.dp),
            onClick = onRegisterClick,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1F),
                        text = stringResource(R.string.register),
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
            text = stringResource(R.string.already_have_account),
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
                    onClick = onLoginNowClick
                ),
            text = stringResource(R.string.login_now),
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

@Preview(showBackground = true)
@Composable
private fun RegistrationContentPreview() {
    MedifyTheme {
        RegistrationContent(
            uiState = RegisterUiState(),
            onFirstNameChange = {},
            onLastNameNameChange = {},
            onIdNumberChange = {},
            onEmailChange = {},
            onPhoneNoChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onPasswordVisibilityChange = {},
            onRegisterClick = {},
            onLoginNowClick = {},
        )
    }
}