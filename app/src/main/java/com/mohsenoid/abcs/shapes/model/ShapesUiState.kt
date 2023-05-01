package com.mohsenoid.abcs.shapes.model

import androidx.annotation.DrawableRes

data class ShapesUiState(
    val isStartVisible: Boolean = true,
    val index: Int = 0,
    @DrawableRes val shape: Int = -1,
)
