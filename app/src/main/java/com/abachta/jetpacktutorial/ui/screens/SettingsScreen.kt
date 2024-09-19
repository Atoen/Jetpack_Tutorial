package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.ui.AppLocale
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.components.LocaleDropdownMenu
import com.abachta.jetpacktutorial.ui.components.ThemeDropdownMenu

@Composable
fun SettingsScreen(
    appTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    appLocale: AppLocale,
    onLocaleSelected: (AppLocale) -> Unit,
    onClear: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ThemeDropdownMenu(
            selectedTheme = appTheme,
            onThemeSelected = onThemeSelected
        )

        Spacer(Modifier.height(16.dp))

        LocaleDropdownMenu(
            selectedLocale = appLocale,
            onLocaleSelected = onLocaleSelected
        )

        Button(onClick = onClear) {
            Text("Clear lessons")
        }
    }
}