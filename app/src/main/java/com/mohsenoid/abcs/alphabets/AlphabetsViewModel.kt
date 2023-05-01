package com.mohsenoid.abcs.alphabets

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.alphabets.model.AlphabetMode
import com.mohsenoid.abcs.alphabets.model.AlphabetsUiState
import com.mohsenoid.abcs.speech.SpeechHelper
import com.mohsenoid.abcs.theme.Blue
import com.mohsenoid.abcs.theme.Green
import com.mohsenoid.abcs.theme.Orange
import com.mohsenoid.abcs.theme.Pink
import com.mohsenoid.abcs.theme.Purple
import com.mohsenoid.abcs.theme.Red
import com.mohsenoid.abcs.theme.Yellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AlphabetsViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    private val _uiState = MutableStateFlow(AlphabetsUiState())
    val uiState by ::_uiState

    fun onModeClicked(selectedMode: AlphabetMode) {
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
            val newChar = if (currentUiState.isInModeSelector) {
                'A'
            } else {
                var nextChar = currentUiState.letter[0] + 1
                if (nextChar !in 'A'..'Z') {
                    nextChar = 'A'
                }
                nextChar
            }

            val color = getColor(newChar)

            val newLetter = newChar.toString()

            speechHelper.speak(newLetter)

            currentUiState.copy(
                isInModeSelector = false,
                letter = newLetter,
                color = color
            )
        }
    }

    private fun getColor(char: Char): Color {
        val colorIndex = char.hashCode() % COLORS.size
        return COLORS[colorIndex]
    }

    companion object {
        val COLORS = listOf(
            Red,
            Pink,
            Purple,
            Blue,
            Green,
            Yellow,
            Orange
        )
    }
}
