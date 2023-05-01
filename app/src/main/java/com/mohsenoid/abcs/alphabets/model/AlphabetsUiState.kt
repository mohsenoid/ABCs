package com.mohsenoid.abcs.alphabets.model

import androidx.compose.ui.graphics.Color
import com.mohsenoid.abcs.theme.White

data class AlphabetsUiState(
    val isInModeSelector: Boolean = true,
    val letter: String = "A",
    val color: Color = White,
    val mode: AlphabetMode = AlphabetMode.UPPERCASE,
)
