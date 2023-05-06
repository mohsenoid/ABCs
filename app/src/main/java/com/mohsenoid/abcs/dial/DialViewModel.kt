package com.mohsenoid.abcs.dial

import androidx.lifecycle.ViewModel
import com.mohsenoid.abcs.speech.SpeechHelper

class DialViewModel(private val speechHelper: SpeechHelper) : ViewModel() {

    fun onClicked(number: Int) {
        speechHelper.speak(number.toString())
    }
}