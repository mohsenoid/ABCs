package com.mohsenoid.abcs.speech

import android.content.Context
import android.content.res.Configuration
import android.speech.tts.TextToSpeech
import androidx.annotation.StringRes
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SpeechHelper(
    private val context: Context,
) {

    private lateinit var localeContext: Context

    private lateinit var textToSpeech: TextToSpeech

    private var initState = InitState.IDLE

    suspend fun init(locale: Locale): Result<Unit> = suspendCoroutine { continuation ->
        initState = InitState.INITIALIZING

        val config = Configuration().apply {
            setTo(context.resources.configuration)
            setLocale(locale)
        }
        this.localeContext = context.createConfigurationContext(config)

        textToSpeech = TextToSpeech(context) { initResult ->
            if (initResult == TextToSpeech.SUCCESS) {
                val languageResult = textToSpeech.setLanguage(locale)
                if (languageResult == TextToSpeech.SUCCESS) {
                    initState = InitState.SUCCESS
                    continuation.resume(Result.success(Unit))
                } else {
                    initState = InitState.ERROR
                    continuation.resume(Result.failure(Exception("Error setting language")))
                }
            } else {
                initState = InitState.ERROR
                continuation.resume(Result.failure(Exception("Error initializing")))
            }
        }
    }

    fun speak(text: String) {
        if (initState == InitState.SUCCESS) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun speak(@StringRes text: Int) {
        if (initState == InitState.SUCCESS) {
            textToSpeech.speak(localeContext.getString(text), TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    enum class InitState {
        IDLE, INITIALIZING, SUCCESS, ERROR
    }
}