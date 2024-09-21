package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
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

        Spacer(Modifier.height(30.dp))

        var openDialog by remember { mutableStateOf(false) }

        TextButton(onClick = {
            openDialog = true
        }) {
            Text(
                text = stringResource(R.string.clear_all_progress),
                color = MaterialTheme.colorScheme.error
            )
        }

        if (openDialog) {

            val dangerColor = MaterialTheme.colorScheme.error
            AlertDialog(
                title = { Text(stringResource(R.string.dialog_clear_title)) },
                text = { Text(stringResource(R.string.dialog_clear_text)) },
                onDismissRequest = { openDialog = false },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.DeleteForever,
                        contentDescription = null,
                        tint = dangerColor
                    )
               },
                confirmButton = {
                    TextButton(onClick = {
                        onClear()
                        openDialog = false
                    }) {
                        Text(
                            text = stringResource(R.string.dialog_clear_progress),
                            color = dangerColor
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        openDialog = false
                    }) {
                        Text(stringResource(R.string.dialog_cancel))
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        onLocaleSelected = {},
        onThemeSelected = {},
        onClear = {},
        appLocale = AppLocale.Polish,
        appTheme = AppTheme.Auto,
    )
}