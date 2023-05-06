package com.mohsenoid.abcs.selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohsenoid.abcs.selector.model.SelectorUiState
import com.mohsenoid.abcs.speech.SpeechHelper
import com.mohsenoid.abcs.util.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class SelectorViewModel(
    private val appState: AppState,
    private val speechHelper: SpeechHelper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SelectorUiState(
            locale = appState.loadLocale(),
        )
    )
    val uiState by ::_uiState

    init {
        initSpeechHelper(appState.loadLocale())
    }

    private fun initSpeechHelper(locale: Locale) {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                isInitializing = true,
                locale = locale
            )
        }

        viewModelScope.launch {
            speechHelper.init(locale)
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    isInitializing = false
                )
            }
        }
    }

    fun onLocaleChanged(newLocale: Locale) {
        appState.saveLocale(newLocale)

        initSpeechHelper(newLocale)
    }
}