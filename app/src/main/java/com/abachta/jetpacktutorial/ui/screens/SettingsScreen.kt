package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.runtime.Composable
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.components.ThemeDropdownMenu
import kotlinx.serialization.Serializable

@Composable
fun SettingsScreen(
    appTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    ThemeDropdownMenu(
        selectedTheme = appTheme,
        onThemeSelected = onThemeSelected
    )
}