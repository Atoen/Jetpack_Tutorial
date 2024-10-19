package com.abachta.jetpacktutorial.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.data.Preferences
import com.abachta.jetpacktutorial.settings.AppLocale
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.LessonPopupOption
import com.abachta.jetpacktutorial.settings.CodeListingFont
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import com.abachta.jetpacktutorial.settings.QuizShufflingOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)

    private val _locale = mutableStateOf<AppLocale>(AppLocale.English)
    private val _theme = mutableStateOf<AppTheme>(AppTheme.Auto)
    private val _dynamicColors = mutableStateOf<DynamicColorsOption>(DynamicColorsOption.Enabled)
    private val _showLessonPopup = mutableStateOf<LessonPopupOption>(LessonPopupOption.Enabled)
    private val _listingFont = mutableStateOf<CodeListingFont>(CodeListingFont.Medium)
    private val _questionShuffling = mutableStateOf<QuizShufflingOption>(QuizShufflingOption.ShuffleQuestions)

    val isReady = _isReady.asStateFlow()

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

    var theme
        get() = _theme.value
        set(value) {
            _theme.value = value
            viewModelScope.launch {
                preferences.setInt(THEME_KEY, value.value)
            }
        }

    var dynamicColors
        get() = _dynamicColors.value
        set(value) {
            _dynamicColors.value = value
            viewModelScope.launch {
                preferences.setBoolean(DYNAMIC_COLORS_KEY, value.enabled)
            }
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

    var questionShuffling
        get() = _questionShuffling.value
        set(value) {
            _questionShuffling.value = value
            viewModelScope.launch {
                preferences.setInt(SHUFFLE_KEY, value.value)
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

            _questionShuffling.value = preferences.getInt(SHUFFLE_KEY)?.let {
                QuizShufflingOption.fromInt(it)
            } ?: QuizShufflingOption.ShuffleQuestions

            val systemLocale = Locale.getDefault()
            val localeTag = AppCompatDelegate.getApplicationLocales()
                .get(0)?.language ?: systemLocale.language

            _locale.value = AppLocale.fromLanguageTag(localeTag)

            _isReady.value = true
        }
    }

    val nonPersistentAccessor by lazy {
        object : AppVisualsAccessor {
            override var theme by _theme
            override var dynamicColors by _dynamicColors
        }
    }

    companion object {
        private const val THEME_KEY = "theme"
        private const val DYNAMIC_COLORS_KEY = "dynamic_colors"
        private const val POPUP_KEY = "popup"
        private const val FONT_KEY = "font"
        private const val SHUFFLE_KEY = "shuffle"
    }
}