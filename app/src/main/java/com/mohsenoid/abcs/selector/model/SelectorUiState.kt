package com.mohsenoid.abcs.selector.model

import java.util.Locale

data class SelectorUiState(
    val isInitializing: Boolean = true,
    val locale: Locale,
)