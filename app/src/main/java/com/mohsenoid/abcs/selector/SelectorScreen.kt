package com.mohsenoid.abcs.selector

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.selector.model.SelectorItem
import com.mohsenoid.abcs.selector.model.SelectorUiState
import com.mohsenoid.abcs.theme.ABCsTheme
import com.mohsenoid.abcs.theme.White
import java.util.Locale

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
private fun LocaleSwitch(
    state: SelectorUiState,
    modifier: Modifier = Modifier,
    onLocaleChanged: (Locale) -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "EN",
            color = White,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = state.locale != Locale.ENGLISH,
            onCheckedChange = { isChecked ->
                onLocaleChanged(if (isChecked) Locale.GERMAN else Locale.ENGLISH)
            },
            thumbContent = { },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onSurface,
                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                checkedBorderColor = Color.Transparent,
                checkedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface,
                uncheckedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedBorderColor = Color.Transparent,
                uncheckedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "DE",
            color = White,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
private fun LocaleSwitchPreview() {
    ABCsTheme {
        LocaleSwitch(
            state = SelectorUiState(locale = Locale.ENGLISH)
        )
    }
}

@Composable
private fun SelectorButton(
    @StringRes text: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(),
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = text)
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun SelectorButtonPreview() {
    ABCsTheme {
        SelectorButton(
            text = R.string.selector_numbers,
            icon = R.drawable.ic_123
        )
    }
}

@Composable
fun SelectorScreen(
    state: SelectorUiState,
    selectorItems: List<SelectorItem>,
    modifier: Modifier = Modifier,
    onLocaleChanged: (newLocale: Locale) -> Unit = {},
) {
    Box(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
    ) {
        if (state.isInitializing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(if (isTablet()) 3 else 2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                items(selectorItems) { selectorItem ->
                    SelectorButton(
                        text = selectorItem.text,
                        icon = selectorItem.icon,
                        onClick = selectorItem.onClick,
                    )
                }
            }
            LocaleSwitch(
                state = state,
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onLocaleChanged = onLocaleChanged
            )
        }
    }
}

@Preview
@Composable
fun SelectorScreenPhonePreview() {
    ABCsTheme {
        SelectorScreen(
            state = SelectorUiState(locale = Locale.ENGLISH),
            selectorItems = selectorItems
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun SelectorScreenTabletPreview() {
    ABCsTheme {
        SelectorScreen(
            state = SelectorUiState(locale = Locale.ENGLISH),
            selectorItems = selectorItems
        )
    }
}

private val selectorItems = listOf(
    SelectorItem(
        text = R.string.selector_alphabets,
        icon = R.drawable.ic_abc,
        onClick = {}
    ),
    SelectorItem(
        text = R.string.selector_numbers,
        icon = R.drawable.ic_123,
        onClick = {}
    ),
    SelectorItem(
        text = R.string.selector_shapes,
        icon = R.drawable.ic_shape,
        onClick = {}
    ),
    SelectorItem(
        text = R.string.selector_colors,
        icon = R.drawable.ic_color,
        onClick = {}
    ),
    SelectorItem(
        text = R.string.selector_dial,
        icon = R.drawable.ic_dial,
        onClick = {}
    ),
)