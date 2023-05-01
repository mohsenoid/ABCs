package com.mohsenoid.abcs.dial

import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.speech.SpeechHelper

class DialViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    fun onClicked(number: Int) {
        val numberName = NUMBERS[number]
        speechHelper.speak(numberName)

    }

    companion object {
        val NUMBERS = listOf(
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
        )
    }
}