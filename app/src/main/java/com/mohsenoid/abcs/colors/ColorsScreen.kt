package com.mohsenoid.abcs.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.colors.model.ColorMode
import com.mohsenoid.abcs.colors.model.ColorsUiState
import com.mohsenoid.abcs.compose.BackButton
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
private fun ModeSelector(
    modifier: Modifier,
    onModeClicked: (selectedMode: ColorMode) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ModeSelectorButton(
            text = "Basic",
            onClick = { onModeClicked(ColorMode.BASIC) })
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelectorButton(
            text = "Advanced",
            onClick = { onModeClicked(ColorMode.ADVANCED) })
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelectorButton(
            text = "Expert",
            onClick = { onModeClicked(ColorMode.EXPERT) })
    }
}

@Composable
private fun ModeSelectorButton(
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(250.dp),
        colors = ButtonDefaults.filledTonalButtonColors(),
    ) {
        Text(
            text = text,
            fontSize = 40.sp,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ModeSelectorPhonePreview() {
    ABCsTheme {
        ModeSelector(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
private fun ModeSelectorTabletPreview() {
    ABCsTheme {
        ModeSelector(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ColorsScreen(
    state: ColorsUiState,
    modifier: Modifier = Modifier,
    onModeClicked: (selectedMode: ColorMode) -> Unit = {},
    onClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .then(
                if (!state.isModeSelectorVisible) {
                    Modifier
                        .background(state.color)
                        .clickable { onClicked() }
                } else {
                    Modifier
                }
            )
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = onBackClicked,
        )
        if (state.isModeSelectorVisible) {
            ModeSelector(
                modifier = Modifier
                    .align(Alignment.Center),
                onModeClicked = onModeClicked,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ColorsScreenPhonePreview() {
    ABCsTheme {
        ColorsScreen(ColorsUiState(isModeSelectorVisible = false))
    }
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun ColorsScreenTabletPreview() {
    ABCsTheme {
        ColorsScreen(ColorsUiState(isModeSelectorVisible = false))
    }
}
