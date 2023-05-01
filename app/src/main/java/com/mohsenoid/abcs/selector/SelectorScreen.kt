package com.mohsenoid.abcs.selector

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.selector.model.SelectorItem
import com.mohsenoid.abcs.theme.ABCsTheme

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp > 840
    } else {
        configuration.screenWidthDp > 600
    }
}

@Composable
fun SelectorScreen(
    selectorItems: List<SelectorItem>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isTablet()) 3 else 2),
        modifier = modifier
            .padding(16.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(selectorItems) { selectorItem ->
            selectorButton(
                text = selectorItem.text,
                icon = selectorItem.icon,
                onClick = selectorItem.onClick,
            )
        }
    }
}

@Preview()
@Composable
fun selectorButtonPreview() {
    ABCsTheme {
        selectorButton(
            text = "123s",
            icon = R.drawable.ic_123
        )
    }
}

@Composable
private fun selectorButton(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(),
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = text
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun SelectorScreenPhonePreview() {
    ABCsTheme {
        SelectorScreen(
            selectorItems = selectorItems
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun SelectorScreenTabletPreview() {
    ABCsTheme {
        SelectorScreen(
            selectorItems = selectorItems
        )
    }
}

private val selectorItems = listOf(
    SelectorItem(
        text = "ABCs",
        icon = R.drawable.ic_abc,
        onClick = {}
    ),
    SelectorItem(
        text = "123s",
        icon = R.drawable.ic_123,
        onClick = {}
    ),
    SelectorItem(
        text = "Shapes",
        icon = R.drawable.ic_shape,
        onClick = {}
    ),
    SelectorItem(
        text = "Colors",
        icon = R.drawable.ic_color,
        onClick = {}
    ),
    SelectorItem(
        text = "Dial",
        icon = R.drawable.ic_dial,
        onClick = {}
    ),
)