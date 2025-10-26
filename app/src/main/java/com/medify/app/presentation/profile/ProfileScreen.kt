package com.medify.app.presentation.profile

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medify.app.R
import com.medify.app.designsystem.component.ContainerNavigationDrawer
import com.medify.app.designsystem.component.MedifyInputField
import com.medify.app.designsystem.theme.CyanBright
import com.medify.app.designsystem.theme.GrayDark
import com.medify.app.designsystem.theme.GrayMedium
import com.medify.app.designsystem.theme.GrayMuted
import com.medify.app.designsystem.theme.GraySoft
import com.medify.app.helper.isValidEmail
import com.medify.app.helper.isValidIndonesianPhone
import com.medify.app.helper.normalizePhone
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onSaveProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val activity = LocalActivity.current

    val accountSettingMenus = remember { viewModel.getAccountSettingMenus() }

    ProfileContent(
        uiState = uiState,
        accountSettingMenus = accountSettingMenus,
        onFirstNameChange = { firstName ->
            viewModel.onEvent(ProfileEvent.OnFirstNameValueChange(firstName))
        },
        onLastNameChange = { lastName ->
            viewModel.onEvent(ProfileEvent.OnLastNameValueChange(lastName))
        },
        onEmailChange = { email ->
            viewModel.onEvent(ProfileEvent.OnEmailValueChange(email))
        },
        onPhoneNoChange = { phoneNo ->
            viewModel.onEvent(ProfileEvent.OnPhoneNoValueChange(phoneNo))
        },
        onIdNumberChange = { idNumber ->
            viewModel.onEvent(ProfileEvent.OnIdNumberValueChane(idNumber))
        },
        onSaveProfileClick = onSaveProfileClick,
        onFeatureNotAvailable = {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.feature_not_available),
                Toast.LENGTH_SHORT
            ).show()
        },
        onLogoutClick = onLogoutClick,
        onSocialMediaClick = {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.feature_not_available),
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun ProfileContent(
    uiState: ProfileUiState,
    accountSettingMenus: List<String>,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNoChange: (String) -> Unit,
    onIdNumberChange: (String) -> Unit,
    onSaveProfileClick: () -> Unit,
    onFeatureNotAvailable: () -> Unit,
    onLogoutClick: () -> Unit,
    onSocialMediaClick: () -> Unit,
) {
    ContainerNavigationDrawer(
        isShowMenu = false,
        onFeatureNotAvailable = onFeatureNotAvailable,
        onLogoutClick = onLogoutClick,
        onSocialMediaClick = onSocialMediaClick
    ) { innerPadding ->
        val context = LocalContext.current

        val coroutineScope = rememberCoroutineScope()

        val accountSettingMenuPagerState = rememberPagerState(pageCount = { accountSettingMenus.size })

        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 24.dp, top = 22.dp, end = 24.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp),
                        spotColor = MaterialTheme.colorScheme.outline,
                        ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(4.dp),
            ) {
                accountSettingMenus.forEachIndexed { index, type ->
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = if (index == accountSettingMenuPagerState.currentPage) CyanBright else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 12.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    coroutineScope.launch {
                                        accountSettingMenuPagerState.animateScrollToPage(index)
                                    }
                                }
                            ),
                        text = type,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(if (index == accountSettingMenuPagerState.currentPage) R.font.proximanova_bold else R.font.proximanova_regular)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = accountSettingMenuPagerState,
                beyondViewportPageCount = 1,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false,
            ) { page ->
                when (accountSettingMenus[page]) {
                    context.getString(R.string.profile_tab_profile) -> MyProfileContent(
                        uiState = uiState,
                        onFirstNameChange = onFirstNameChange,
                        onLastNameChange = onLastNameChange,
                        onEmailChange = onEmailChange,
                        onPhoneNoChange = onPhoneNoChange,
                        onIdNumberChange = onIdNumberChange,
                        onSaveProfileClick = onSaveProfileClick
                    )

                    context.getString(R.string.profile_tab_settings) -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.feature_not_available),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun MyProfileContent(
    uiState: ProfileUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNoChange: (String) -> Unit,
    onIdNumberChange: (String) -> Unit,
    onSaveProfileClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 40.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = MaterialTheme.colorScheme.outline,
                ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                val (photoProfile, name, role, profileInformation) = createRefs()

                Image(
                    modifier = Modifier
                        .constrainAs(photoProfile) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top, 40.dp)
                            horizontalBias = 0F
                        }
                        .padding(start = 16.dp)
                        .size(50.dp),
                    painter = painterResource(R.drawable.img_profile),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .constrainAs(name) {
                            start.linkTo(photoProfile.end)
                            top.linkTo(photoProfile.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(role.top)
                            width = Dimension.preferredWrapContent
                            horizontalBias = 0F
                        }
                        .padding(horizontal = 16.dp),
                    text = "Abdan Zaki Alifian",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    modifier = Modifier
                        .constrainAs(role) {
                            start.linkTo(photoProfile.end)
                            top.linkTo(name.bottom)
                            end.linkTo(parent.end)
                            bottom.linkTo(photoProfile.bottom)
                            width = Dimension.preferredWrapContent
                            horizontalBias = 0F
                        }
                        .padding(horizontal = 16.dp),
                    text = "Android Developer",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                )

                Text(
                    modifier = Modifier
                        .constrainAs(profileInformation) {
                            start.linkTo(parent.start)
                            top.linkTo(photoProfile.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                            horizontalBias = 0F
                        }
                        .padding(top = 40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2F),
                            shape = RoundedCornerShape(topStart = 20.dp)
                        )
                        .padding(vertical = 10.dp, horizontal = 16.dp),
                    text = stringResource(R.string.profile_completion_message),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }

            Text(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.profile_select_data_title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = CyanBright)
                        .padding(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Filled.PersonPin,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Column(modifier = Modifier.weight(1F).padding(horizontal = 12.dp)) {
                    Text(
                        text = stringResource(R.string.profile_personal_data_title),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = GrayDark
                    )

                    Text(
                        text = stringResource(R.string.profile_personal_data_description),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                            fontSize = 10.sp,
                            color = GrayMuted
                        ),
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = GraySoft)
                        .padding(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = GrayMedium
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.first_name_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp, end = 12.dp),
                value = uiState.firstName,
                placeholder = stringResource(R.string.first_name_placeholder),
                onValueChange = onFirstNameChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Done),
                trailingIcon = {
                    if (uiState.firstName.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp),
                            onClick = {
                                onFirstNameChange("")
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.last_name_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp, end = 12.dp),
                value = uiState.lastName,
                placeholder = stringResource(R.string.last_name_placeholder),
                onValueChange = onLastNameChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Done),
                trailingIcon = {
                    if (uiState.lastName.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp),
                            onClick = {
                                onLastNameChange("")
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.email_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp, end = 12.dp),
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.phone_number_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp, end = 12.dp),
                value = uiState.phoneNo.normalizePhone(),
                placeholder = stringResource(R.string.phone_number_placeholder),
                errorMessage = if (uiState.phoneNo.isBlank() || uiState.phoneNo.isValidIndonesianPhone()) "" else stringResource(R.string.error_phone_invalid),
                onValueChange = { phoneNo ->
                    if (phoneNo.length <= 13) {
                        onPhoneNoChange(phoneNo)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                trailingIcon = {
                    if (uiState.phoneNo.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp),
                            onClick = {
                                onPhoneNoChange("")
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                text = stringResource(R.string.id_number_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            MedifyInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp, end = 12.dp),
                value = uiState.idNumber,
                placeholder = stringResource(R.string.id_number_placeholder),
                errorMessage = if (uiState.idNumber.isBlank() || uiState.idNumber.length >= 16) "" else stringResource(R.string.error_nik_length),
                onValueChange = { idNumber ->
                    if (idNumber.length <= 16) {
                        onIdNumberChange(idNumber)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                trailingIcon = {
                    if (uiState.idNumber.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp),
                            onClick = {
                                onIdNumberChange("")
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

            Row(modifier = Modifier.padding(start = 24.dp, top = 40.dp, end = 24.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_information),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.profile_security_notice),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                        fontSize = 12.sp,
                        color = GrayMedium
                    )
                )
            }

            Button(
                modifier = Modifier.padding(start = 24.dp, top = 34.dp, end = 24.dp, bottom = 24.dp),
                enabled = (uiState.firstName.isNotBlank() && uiState.lastName.isNotBlank()) &&
                        (uiState.idNumber.isNotBlank() && uiState.idNumber.length == 16) &&
                        (uiState.email.isNotBlank() && uiState.email.isValidEmail()) &&
                        (uiState.phoneNo.isNotBlank() && uiState.phoneNo.isValidIndonesianPhone()),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues.Zero,
                onClick = onSaveProfileClick,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(R.string.profile_save_button),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    MaterialTheme {
        ProfileContent(
            uiState = ProfileUiState(),
            accountSettingMenus = listOf("My Profile", "Settings"),
            onFirstNameChange = {},
            onLastNameChange = {},
            onEmailChange = {},
            onPhoneNoChange = {},
            onIdNumberChange = {},
            onSaveProfileClick = {},
            onFeatureNotAvailable = {},
            onLogoutClick = {},
            onSocialMediaClick = {}
        )
    }
}