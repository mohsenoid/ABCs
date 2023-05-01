package com.mohsenoid.abcs.alphabets

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.alphabets.model.AlphabetMode
import com.mohsenoid.abcs.alphabets.model.AlphabetsUiState
import com.mohsenoid.abcs.compose.BackButton
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
private fun ModeSelector(
    modifier: Modifier,
    onModeClicked: (selectedMode: AlphabetMode) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ModeSelectorButton(
            text = "A",
            onClick = { onModeClicked(AlphabetMode.UPPERCASE) })
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelectorButton(
            text = "a",
            onClick = { onModeClicked(AlphabetMode.LOWERCASE) })
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelectorButton(
            text = "A a",
            onClick = { onModeClicked(AlphabetMode.MIX) })
    }
}

@Composable
private fun ModeSelectorButton(
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(200.dp),
        colors = ButtonDefaults.filledTonalButtonColors(),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
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
fun AlphabetsScreen(
    state: AlphabetsUiState,
    modifier: Modifier = Modifier,
    onModeClicked: (selectedMode: AlphabetMode) -> Unit = {},
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
                if (!state.isInModeSelector) {
                    Modifier
                        .clickable { onClicked() }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = onBackClicked,
        )
        if (state.isInModeSelector) {
            ModeSelector(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(32.dp),
                onModeClicked = onModeClicked,
            )
        } else {
            val text = when (state.mode) {
                AlphabetMode.UPPERCASE -> state.letter.uppercase()
                AlphabetMode.LOWERCASE -> state.letter.lowercase()
                AlphabetMode.MIX -> state.letter.uppercase() + " " + state.letter.lowercase()
            }
            Text(
                text = text,
                color = state.color,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AlphabetsScreenPhonePreview() {
    ABCsTheme {
        AlphabetsScreen(state = AlphabetsUiState(isInModeSelector = false), modifier = Modifier.fillMaxSize())
    }
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun AlphabetsScreenTabletPreview() {
    ABCsTheme {
        AlphabetsScreen(state = AlphabetsUiState(isInModeSelector = false), modifier = Modifier.fillMaxSize())
    }
}
