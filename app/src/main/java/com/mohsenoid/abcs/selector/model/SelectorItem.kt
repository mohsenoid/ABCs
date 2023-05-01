package com.mohsenoid.abcs.selector.model

import androidx.annotation.DrawableRes

data class SelectorItem(
    val text: String,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit,
)
