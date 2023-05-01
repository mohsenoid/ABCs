package com.mohsenoid.abcs.colors.model

import androidx.compose.ui.graphics.Color

data class ColorsUiState(
    val isModeSelectorVisible: Boolean = true,
    val index: Int = 0,
    val color: Color = Color.Red,
    val mode: ColorMode = ColorMode.BASIC,
)
