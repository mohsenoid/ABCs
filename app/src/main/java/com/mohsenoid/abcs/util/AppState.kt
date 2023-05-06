package com.mohsenoid.abcs.util

import android.content.Context
import androidx.core.content.edit
import java.util.Locale

class AppState(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(DATA_STORE_NAME, Context.MODE_PRIVATE)

    fun loadLocale(): Locale {
        return sharedPreferences.getString(KEY_LOCALE, null)?.let { language ->
            Locale.forLanguageTag(language)
        } ?: Locale.ENGLISH
    }

    fun saveLocale(locale: Locale) {
        sharedPreferences.edit {
            putString(KEY_LOCALE, locale.language)
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "app_state"
        private const val KEY_LOCALE = "locale"
    }
}