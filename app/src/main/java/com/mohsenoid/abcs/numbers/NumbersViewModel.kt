package com.mohsenoid.abcs.numbers

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.numbers.model.NumbersMax
import com.mohsenoid.abcs.numbers.model.NumbersUiState
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

class NumbersViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    private val _uiState = MutableStateFlow(NumbersUiState())
    val uiState by ::_uiState

    fun onMaxSelectorClicked(max: NumbersMax) {
        _uiState.update { currentUiState ->
            currentUiState.copy(max = max.value)
        }

        proceed()
    }

    fun onClicked() {
        proceed()
    }

    private fun proceed() {
        _uiState.update { currentUiState ->
            val newNumber = if (currentUiState.isMaxSelectorVisible) {
                1
            } else {
                var nextDigit = currentUiState.number + 1
                if (nextDigit !in 1..currentUiState.max) {
                    nextDigit = 1
                }
                nextDigit
            }

            val color = getColor(newNumber)

            speechHelper.speak(newNumber.toString())

            currentUiState.copy(
                isMaxSelectorVisible = false,
                number = newNumber,
                color = color
            )
        }
    }

    private fun getColor(digit: Int): Color {
        val colorIndex = digit % COLORS.size
        return COLORS[colorIndex]
    }

    companion object {
        val COLORS: List<Color>
            get() = listOf(
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