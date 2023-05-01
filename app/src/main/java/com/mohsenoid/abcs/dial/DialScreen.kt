package com.mohsenoid.abcs.dial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.compose.BackButton
import com.mohsenoid.abcs.selector.isTablet
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
private fun DialButton(
    number: Int,
    modifier: Modifier = Modifier,
    onClick: (number: Int) -> Unit = {},
) {
    Button(
        onClick = { onClick(number) },
        modifier = modifier,
        colors = ButtonDefaults.filledTonalButtonColors(),
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
fun DialButtonPreview() {
    ABCsTheme {
        DialButton(1)
    }
}

@Composable
fun DialScreen(
    modifier: Modifier = Modifier,
    onClicked: (number: Int) -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = onBackClicked,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(count = 12) { index ->
                val buttonModifier = Modifier
                    .then(
                        if (isTablet()) {
                            Modifier
                                .aspectRatio(ratio = 4F)
                        } else {
                            Modifier
                        }
                    )


                val number = index + 1
                if (number in 1..9) {
                    DialButton(
                        number = number,
                        modifier = buttonModifier,
                        onClick = onClicked
                    )
                } else if (number == 11) {
                    DialButton(
                        number = 0,
                        modifier = buttonModifier,
                        onClick = onClicked
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DialScreenPhonePreview() {
    ABCsTheme {
        DialScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun DialScreenTabletPreview() {
    ABCsTheme {
        DialScreen(modifier = Modifier.fillMaxSize())
    }
}
