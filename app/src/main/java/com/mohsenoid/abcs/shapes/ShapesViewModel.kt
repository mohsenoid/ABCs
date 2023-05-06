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
        val SHAPES: List<Pair<Int, Int>>
            get() = listOf(
                R.string.shape_square to R.drawable.square,
                R.string.shape_circle to R.drawable.circle,
                R.string.shape_triangle to R.drawable.triangle,
                R.string.shape_oval to R.drawable.oval,
                R.string.shape_rectangle to R.drawable.rectangle,
                R.string.shape_diamond to R.drawable.diamond,
                R.string.shape_heart to R.drawable.heart,
                R.string.shape_arrow to R.drawable.arrow,
                R.string.shape_pentagon to R.drawable.pentagon,
                R.string.shape_hexagon to R.drawable.hexagon,
                R.string.shape_cross to R.drawable.cross,
                R.string.shape_star to R.drawable.star,
                R.string.shape_parallelogram to R.drawable.parallelogram,
                R.string.shape_trapezoid to R.drawable.trapezoid,
            )
    }
}
