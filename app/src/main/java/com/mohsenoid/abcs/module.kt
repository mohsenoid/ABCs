package com.mohsenoid.abcs

import com.mohsenoid.abcs.alphabets.AlphabetsViewModel
import com.mohsenoid.abcs.colors.ColorsViewModel
import com.mohsenoid.abcs.dial.DialViewModel
import com.mohsenoid.abcs.numbers.NumbersViewModel
import com.mohsenoid.abcs.selector.SelectorViewModel
import com.mohsenoid.abcs.shapes.ShapesViewModel
import com.mohsenoid.abcs.speech.SpeechHelper
import com.mohsenoid.abcs.util.AppState
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        AppState(context = androidContext())
    }

    single {
        SpeechHelper(context = androidContext())
    }

    viewModel {
        SelectorViewModel(appState = get(), speechHelper = get())
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