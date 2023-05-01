package com.mohsenoid.abcs

import com.mohsenoid.abcs.alphabets.AlphabetsViewModel
import com.mohsenoid.abcs.colors.ColorsViewModel
import com.mohsenoid.abcs.dial.DialViewModel
import com.mohsenoid.abcs.numbers.NumbersViewModel
import com.mohsenoid.abcs.shapes.ShapesViewModel
import com.mohsenoid.abcs.speech.SpeechHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        SpeechHelper(context = get())
    }

    viewModel {
        AlphabetsViewModel(speechHelper = get())
    }

    viewModel {
        NumbersViewModel(speechHelper = get())
    }

    viewModel {
        ShapesViewModel(speechHelper = get())
    }

    viewModel {
        ColorsViewModel(speechHelper = get())
    }

    viewModel {
        DialViewModel(speechHelper = get())
    }
}