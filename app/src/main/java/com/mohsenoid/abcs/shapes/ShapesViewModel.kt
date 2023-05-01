package com.mohsenoid.abcs.shapes

import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.R
import com.mohsenoid.abcs.shapes.model.ShapesUiState
import com.mohsenoid.abcs.speech.SpeechHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ShapesViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    private val _uiState = MutableStateFlow(ShapesUiState())
    val uiState by ::_uiState

    fun onClicked() {
        _uiState.update { currentUiState ->
            val newIndex = if (currentUiState.isStartVisible) {
                0
            } else {
                val nextIndex = currentUiState.index + 1

                if (nextIndex !in SHAPES.indices) {
                    0
                } else {
                    nextIndex
                }
            }

            val shape = SHAPES[newIndex]

            val name = shape.first

            speechHelper.speak(name)

            currentUiState.copy(
                isStartVisible = false,
                index = newIndex,
                shape = shape.second
            )
        }
    }

    companion object {
        val SHAPES: List<Pair<String, Int>>
            get() = listOf(
                "Square" to R.drawable.square,
                "Circle" to R.drawable.circle,
                "Triangle" to R.drawable.triangle,
                "Oval" to R.drawable.oval,
                "Rectangle" to R.drawable.rectangle,
                "Diamond" to R.drawable.diamond,
                "Heart" to R.drawable.heart,
                "Arrow" to R.drawable.arrow,
                "Pentagon" to R.drawable.pentagon,
                "Hexagon" to R.drawable.hexagon,
                "Cross" to R.drawable.cross,
                "Star" to R.drawable.star,
                "Parallelogram" to R.drawable.parallelogram,
                "Trapezoid" to R.drawable.trapezoid,
            )
    }
}
