package com.mohsenoid.abcs.numbers

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
import com.mohsenoid.abcs.compose.BackButton
import com.mohsenoid.abcs.numbers.model.NumbersMax
import com.mohsenoid.abcs.numbers.model.NumbersUiState
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
private fun MaxSelector(
    modifier: Modifier,
    onMaxClicked: (selectedMax: NumbersMax) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MaxSelectorButton(NumbersMax.BASIC, onMaxClicked)
        Spacer(modifier = Modifier.height(16.dp))
        MaxSelectorButton(NumbersMax.ADVANCED, onMaxClicked)
        Spacer(modifier = Modifier.height(16.dp))
        MaxSelectorButton(NumbersMax.EXPERT, onMaxClicked)
        Spacer(modifier = Modifier.height(16.dp))
        MaxSelectorButton(NumbersMax.MASTER, onMaxClicked)

    }
}

@Composable
private fun MaxSelectorButton(
    numberMax: NumbersMax,
    onMaxClicked: (selectedMax: NumbersMax) -> Unit,
) {
    Button(
        onClick = { onMaxClicked(numberMax) },
        modifier = Modifier.width(200.dp),
        colors = ButtonDefaults.filledTonalButtonColors(),
    ) {
        Text(
            text = numberMax.value.toString(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MaxSelectorPhonePreview() {
    ABCsTheme {
        MaxSelector(
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
        MaxSelector(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun NumbersScreen(
    state: NumbersUiState,
    modifier: Modifier = Modifier,
    onMaxClicked: (selectedMax: NumbersMax) -> Unit = {},
    onClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Box(modifier = modifier
        .paint(
            painter = painterResource(id = R.drawable.bg),
            contentScale = ContentScale.Crop
        )
        .then(
            if (!state.isMaxSelectorVisible) {
                Modifier.clickable {
                    onClicked()
                }
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
        if (state.isMaxSelectorVisible) {
            MaxSelector(
                modifier = Modifier
                    .align(Alignment.Center),
                onMaxClicked = onMaxClicked,
            )
        } else {
            Text(
                text = state.number.toString(),
                modifier = Modifier
                    .align(Alignment.Center),
                color = state.color,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NumbersScreenPhonePreview() {
    ABCsTheme {
        NumbersScreen(NumbersUiState(isMaxSelectorVisible = false))
    }
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun NumbersScreenTabletPreview() {
    ABCsTheme {
        NumbersScreen(NumbersUiState(isMaxSelectorVisible = false))
    }
}
