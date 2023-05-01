package com.mohsenoid.abcs.compose

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.PlayArrow,
        contentDescription = "Start",
        modifier = modifier.size(140.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}

@Preview(showSystemUi = true)
@Composable
fun StartButtonPreview() {
    StartButton()
}