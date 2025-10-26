package com.medify.app.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.medify.app.R
import com.medify.app.designsystem.theme.GrayRegular
import com.medify.app.designsystem.theme.MedifyTheme
import com.medify.app.designsystem.theme.RedAlert
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerNavigationDrawer(
    onFeatureNotAvailable: () -> Unit,
    onMyProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSocialMediaClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = true,
            scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F),
            drawerContent = {
                ModalNavigationDrawerContent(
                    onCloseClick = {
                        coroutineScope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            }
                        }
                    },
                    onMyProfileClick = onMyProfileClick,
                    onSettingsClick = onSettingsClick,
                    onLogoutClick = onLogoutClick,
                    onSocialMediaClick = onSocialMediaClick
                )
            },
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {},
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            if (drawerState.isClosed) {
                                                drawerState.open()
                                            } else {
                                                drawerState.close()
                                            }
                                        }
                                    },
                                    content = {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_humberger_menu),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = onFeatureNotAvailable,
                                    content = {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_chart_menu),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )

                                IconButton(
                                    onClick = onFeatureNotAvailable,
                                    content = {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_notification_menu),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            }
                        )
                    },
                    content = content,
                )
            }
        }
    }
}

@Composable
private fun ModalNavigationDrawerContent(
    onCloseClick: () -> Unit,
    onMyProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSocialMediaClick: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (drawerContent, closeIcon) = createRefs()

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(drawerContent) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxHeight()
                    .fillMaxWidth(0.80F)
                    .background(MaterialTheme.colorScheme.background)
                    .statusBarsPadding()
                    .systemBarsPadding()
                    .padding(24.dp),
            ) {
                val (photoProfile, name, role, profile, setting, logout, faq, tnc, socialMedia) = createRefs()

                Image(
                    modifier = Modifier
                        .constrainAs(photoProfile) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            horizontalBias = 0F
                        }
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
                        .padding(start = 16.dp),
                    text = "Abdan Zaki Alifian",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier
                        .constrainAs(role) {
                            start.linkTo(name.start)
                            top.linkTo(name.bottom)
                            end.linkTo(parent.end)
                            bottom.linkTo(photoProfile.bottom)
                            width = Dimension.preferredWrapContent
                            horizontalBias = 0F
                        }
                        .padding(start = 16.dp),
                    text = "Android Developer",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 10.sp),
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.6F)
                        .constrainAs(profile) {
                            start.linkTo(parent.start)
                            top.linkTo(photoProfile.bottom)
                            end.linkTo(parent.end)
                            horizontalBias = 0F
                        }
                        .padding(top = 20.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = onMyProfileClick
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(vertical = 10.dp),
                        text = stringResource(R.string.my_profile),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.gilroy_semi_bold)),
                            fontSize = 12.sp,
                            color = GrayRegular
                        )
                    )

                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = null,
                        tint = GrayRegular
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.6F)
                        .constrainAs(setting) {
                            start.linkTo(parent.start)
                            top.linkTo(profile.bottom)
                            end.linkTo(parent.end)
                            horizontalBias = 0F
                        }
                        .padding(top = 10.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = onSettingsClick
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(vertical = 10.dp),
                        text = stringResource(R.string.settings),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.gilroy_semi_bold)),
                            fontSize = 12.sp,
                            color = GrayRegular
                        )
                    )

                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = null,
                        tint = GrayRegular
                    )
                }

                Button(
                    modifier = Modifier
                        .constrainAs(logout) {
                            start.linkTo(parent.start)
                            top.linkTo(setting.bottom)
                            end.linkTo(parent.end)
                            horizontalBias = 0F
                        }
                        .padding(top = 40.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RedAlert),
                    onClick = onLogoutClick,
                    content = {
                        Text(
                            modifier = Modifier.padding(horizontal = 40.dp),
                            text = stringResource(R.string.logout),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                )

                Row(
                    modifier = Modifier
                        .constrainAs(socialMedia) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(logout.bottom, margin = 40.dp)
                            width = Dimension.preferredWrapContent
                        }
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = onSocialMediaClick
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.follow_us_on),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(
                        painter = painterResource(R.drawable.ic_facebook),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(R.drawable.ic_instagram),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(R.drawable.ic_twitter),
                        contentDescription = null,
                    )
                }

                createHorizontalChain(faq, tnc, chainStyle = ChainStyle.SpreadInside)

                Text(
                    modifier = Modifier.constrainAs(faq) {
                        start.linkTo(parent.start)
                        end.linkTo(tnc.start, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 0F
                    },
                    text = stringResource(R.string.faq),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.outline
                )

                Text(
                    modifier = Modifier.constrainAs(tnc) {
                        start.linkTo(faq.end, margin = 8.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.preferredWrapContent
                        horizontalBias = 0F
                    },
                    text = stringResource(R.string.terms_and_conditions),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.outline
                )
            }

            IconButton(
                modifier = Modifier
                    .constrainAs(closeIcon) {
                        top.linkTo(parent.top, margin = 10.dp)
                        end.linkTo(drawerContent.start, margin = 2.dp)
                    }
                    .statusBarsPadding(),
                onClick = onCloseClick,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_circle_close),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalNavigationDrawerContentPreview() {
    MedifyTheme {
        ModalNavigationDrawerContent(
            onCloseClick = {},
            onMyProfileClick = {},
            onSettingsClick = {},
            onLogoutClick = {},
            onSocialMediaClick = {}
        )
    }
}