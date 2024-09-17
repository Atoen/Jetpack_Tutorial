package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDropdownMenu(
    selectedTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    val icon = when (selectedTheme) {
        AppTheme.Light -> Icons.Filled.LightMode
        AppTheme.Dark -> Icons.Filled.DarkMode
        AppTheme.Auto -> if (isSystemInDarkTheme()) Icons.Filled.DarkMode else Icons.Filled.LightMode
    }

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            readOnly = true,
            value = stringResource(selectedTheme.displayNameResId),
            onValueChange = { },
            label = { Text(stringResource(R.string.theme)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            },
            modifier = Modifier
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            AppTheme.entries.forEach { theme ->
                DropdownMenuItem(
                    onClick = {
                        onThemeSelected(theme)
                        expanded = false
                    },
                    text = { Text(stringResource(theme.displayNameResId)) }
                )
            }
        }
    }
}