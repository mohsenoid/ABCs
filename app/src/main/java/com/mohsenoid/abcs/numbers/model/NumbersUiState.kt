package com.mohsenoid.abcs.numbers.model

import androidx.compose.ui.graphics.Color
import com.mohsenoid.abcs.theme.White

data class NumbersUiState(
    val isMaxSelectorVisible: Boolean = true,
    val number: Int = 1,
    val color: Color = White,
    val max: Int = NumbersMax.BASIC.value,
)
