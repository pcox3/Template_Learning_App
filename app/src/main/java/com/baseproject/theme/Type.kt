package com.baseproject.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.baseproject.R

// Set of Material typography styles to start with
val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = largeText.value.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = smallText.value.sp,
        lineHeight = mediumText.value.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = smallText.value.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Font(R.font.font_bold).toFontFamily(),
        fontWeight = FontWeight.W400,
        fontSize = mediumText.value.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Font(R.font.font_bold).toFontFamily(),
        fontWeight = FontWeight.W300,
        fontSize = smallText.value.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Font(R.font.font_bold).toFontFamily(),
        fontWeight = FontWeight.W500,
        fontSize = largeText.value.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = smallestText.value.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = smallText.value.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Font(R.font.font_regular).toFontFamily(),
        fontWeight = FontWeight.W300,
        fontSize = smallText.value.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Font(R.font.font_bold).toFontFamily(),
        fontWeight = FontWeight.W400,
        fontSize = mediumText.value.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Font(R.font.font_bold).toFontFamily(),
        fontWeight = FontWeight.W500,
        fontSize = largeText.value.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )

)