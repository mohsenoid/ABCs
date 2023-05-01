package com.mohsenoid.abcs.shapes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.compose.BackButton
import com.mohsenoid.abcs.compose.StartButton
import com.mohsenoid.abcs.shapes.model.ShapesUiState
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
fun ShapesScreen(
    state: ShapesUiState,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .clickable {
                onClicked()
            },
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = onBackClicked,
        )
        if (state.isStartVisible) {
            StartButton(
                modifier = Modifier
                    .align(Alignment.Center),
            )
        } else {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = state.shape),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShapesScreenPhonePreview() {
    ABCsTheme {
        ShapesScreen(ShapesUiState(isStartVisible = false, shape = R.drawable.square))
    }
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun ShapesScreenTabletPreview() {
    ABCsTheme {
        ShapesScreen(ShapesUiState(isStartVisible = false, shape = R.drawable.square))
    }
}
