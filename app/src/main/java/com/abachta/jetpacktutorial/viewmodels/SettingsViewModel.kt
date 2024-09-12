package com.abachta.jetpacktutorial.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.data.Preferences
import com.abachta.jetpacktutorial.ui.AppTheme
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
    private val _theme = mutableStateOf(AppTheme.Auto)

    val isReady = _isReady.asStateFlow()

    var theme: AppTheme
        get() = _theme.value
        set(value) {
            _theme.value = value
            viewModelScope.launch {
                preferences.setInt("theme", theme.value)
            }
        }

    init {
        viewModelScope.launch {
            theme = preferences.getInt("theme")?.let { AppTheme.fromInt(it) } ?: AppTheme.Auto
        }
    }
}