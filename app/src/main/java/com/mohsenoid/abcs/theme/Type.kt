package com.mohsenoid.abcs.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mohsenoid.abcs.R

val lapsusProBoldFont = FontFamily(Font(R.font.lapsus_pro_bold))

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = lapsusProBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 300.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.2).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = lapsusProBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = lapsusProBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = lapsusProBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = lapsusProBoldFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
)