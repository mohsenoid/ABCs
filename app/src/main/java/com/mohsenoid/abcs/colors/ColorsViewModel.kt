package com.mohsenoid.abcs.colors

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.colors.model.ColorMode
import com.mohsenoid.abcs.colors.model.ColorsUiState
import com.mohsenoid.abcs.speech.SpeechHelper
import com.mohsenoid.abcs.theme.Azure
import com.mohsenoid.abcs.theme.Beige
import com.mohsenoid.abcs.theme.Black
import com.mohsenoid.abcs.theme.Blue
import com.mohsenoid.abcs.theme.Brown
import com.mohsenoid.abcs.theme.Coral
import com.mohsenoid.abcs.theme.Cream
import com.mohsenoid.abcs.theme.Crimson
import com.mohsenoid.abcs.theme.Cyan
import com.mohsenoid.abcs.theme.Gold
import com.mohsenoid.abcs.theme.Gray
import com.mohsenoid.abcs.theme.Green
import com.mohsenoid.abcs.theme.Indigo
import com.mohsenoid.abcs.theme.Khaki
import com.mohsenoid.abcs.theme.Lavender
import com.mohsenoid.abcs.theme.Lime
import com.mohsenoid.abcs.theme.Magenta
import com.mohsenoid.abcs.theme.Maroon
import com.mohsenoid.abcs.theme.Navy
import com.mohsenoid.abcs.theme.Orange
import com.mohsenoid.abcs.theme.Pink
import com.mohsenoid.abcs.theme.Purple
import com.mohsenoid.abcs.theme.Red
import com.mohsenoid.abcs.theme.White
import com.mohsenoid.abcs.theme.Yellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ColorsViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    private val _uiState = MutableStateFlow(ColorsUiState())
    val uiState by ::_uiState

    fun onModeClicked(selectedMode: ColorMode) {
        _uiState.update { currentUiState ->
            currentUiState.copy(mode = selectedMode)
        }

        proceed()
    }

    fun onClicked() {
        proceed()
    }

    private fun proceed() {
        _uiState.update { currentUiState ->
            val modeColors = modeColors(currentUiState.mode)

            val newIndex = if (currentUiState.isModeSelectorVisible) {
                0
            } else {
                val nextIndex = currentUiState.index + 1

                if (nextIndex !in modeColors.indices) {
                    0
                } else {
                    nextIndex
                }
            }

            val color = modeColors[newIndex]

            val name = color.first

            speechHelper.speak(name)

            currentUiState.copy(
                isModeSelectorVisible = false,
                index = newIndex,
                color = color.second
            )
        }
    }

    private fun modeColors(mode: ColorMode) = when (mode) {
        ColorMode.BASIC -> BASIC_COLORS
        ColorMode.ADVANCED -> ADVANCED_COLORS
        ColorMode.EXPERT -> EXPERT_COLORS
    }

    companion object {
        val BASIC_COLORS: List<Pair<Int, Color>>
            get() = listOf(
                R.string.color_red to Red,
                R.string.color_green to Green,
                R.string.color_blue to Blue,
                R.string.color_white to White,
                R.string.color_black to Black,
            )

        val ADVANCED_COLORS: List<Pair<Int, Color>>
            get() = listOf(
                R.string.color_cyan to Cyan,
                R.string.color_pink to Pink,
                R.string.color_magenta to Magenta,
                R.string.color_yellow to Yellow,
                R.string.color_orange to Orange,
                R.string.color_purple to Purple,
                R.string.color_brown to Brown,
                R.string.color_gray to Gray,
            )

        val EXPERT_COLORS: List<Pair<Int, Color>>
            get() = listOf(
                R.string.color_lime to Lime,
                R.string.color_maroon to Maroon,
                R.string.color_lavender to Lavender,
                R.string.color_coral to Coral,
                R.string.color_navy to Navy,
                R.string.color_beige to Beige,
                R.string.color_cream to Cream,
                R.string.color_khaki to Khaki,
                R.string.color_gold to Gold,
                R.string.color_crimson to Crimson,
                R.string.color_indigo to Indigo,
                R.string.color_azure to Azure,
            )
    }
}