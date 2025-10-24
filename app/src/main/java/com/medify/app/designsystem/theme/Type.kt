package com.medify.app.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.medify.app.R

private val Gilroy = FontFamily(
    Font(resId = R.font.gilroy_regular, weight = FontWeight.Normal),
    Font(resId = R.font.gilroy_medium, weight = FontWeight.Medium),
    Font(resId = R.font.gilroy_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.gilroy_bold, weight = FontWeight.Bold),
    Font(resId = R.font.gilroy_extra_bold, weight = FontWeight.ExtraBold),
)
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    displayMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    displaySmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    headlineLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    headlineMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    headlineSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    titleLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    titleMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    titleSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    bodyLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    bodyMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    bodySmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    labelLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    labelMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    ),
    labelSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textDirection = TextDirection.Ltr,
        textAlign = TextAlign.Start
    )
)