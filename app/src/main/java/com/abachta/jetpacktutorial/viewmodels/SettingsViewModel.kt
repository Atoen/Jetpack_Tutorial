package com.abachta.jetpacktutorial.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.data.Preferences
import com.abachta.jetpacktutorial.settings.AppLocale
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.LessonPopupOption
import com.abachta.jetpacktutorial.settings.CodeListingFont
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)

    private val _theme = mutableStateOf<AppTheme>(AppTheme.Auto)
    private val _locale = mutableStateOf<AppLocale>(AppLocale.English)
    private val _showLessonPopup = mutableStateOf<LessonPopupOption>(LessonPopupOption.Enabled)
    private val _listingFont = mutableStateOf<CodeListingFont>(CodeListingFont.Medium)

    val isReady = _isReady.asStateFlow()

    var theme
        get() = _theme.value
        set(value) {
            _theme.value = value
            viewModelScope.launch {
                preferences.setInt(THEME_KEY, value.value)
            }
        }

    var locale
        get() = _locale.value
        set(value) {
            _locale.value = value
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    value.tag
                )
            )
        }

    var lessonPopup
        get() = _showLessonPopup.value
        set(value) {
            _showLessonPopup.value = value
            viewModelScope.launch {
                preferences.setBoolean(POPUP_KEY, value.enabled)
            }
        }

    var listingFont
        get() = _listingFont.value
        set(value) {
            _listingFont.value = value
            viewModelScope.launch {
                preferences.setInt(FONT_KEY, value.value)
            }
        }

    init {
        viewModelScope.launch {
            _theme.value = preferences.getInt(THEME_KEY)?.let {
                AppTheme.fromInt(it)
            } ?: AppTheme.Auto

            _showLessonPopup.value = preferences.getBoolean(POPUP_KEY)?.let {
                LessonPopupOption.fromBoolean(it)
            } ?: LessonPopupOption.Enabled

            _listingFont.value = preferences.getInt(FONT_KEY)?.let {
                CodeListingFont.fromInt(it)
            } ?: CodeListingFont.Medium

            val localeTag = AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en"
            _locale.value = AppLocale.fromLanguageTag(localeTag)

            _isReady.value = true
        }
    }

    companion object {
        private const val THEME_KEY = "theme"
        private const val POPUP_KEY = "popup"
        private const val FONT_KEY = "font"
    }
}