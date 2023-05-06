package com.mohsenoid.abcs.selector.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SelectorItem(
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit,
)
