package com.abachta.jetpacktutorial.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.abachta.jetpacktutorial.ui.AppLocale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaleDropdownMenu(
    selectedLocale: AppLocale,
    onLocaleSelected: (AppLocale) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = stringResource(selectedLocale.displayNameResId),
            onValueChange = { },
            label = { Text(stringResource(R.string.select_language)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            leadingIcon = {
                Text(selectedLocale.flag)
            },
            modifier = Modifier
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            AppLocale.entries.forEach { locale ->
                DropdownMenuItem(
                    text = { Text(stringResource(locale.displayNameResId)) },
                    leadingIcon = { Text(locale.flag) },
                    onClick = {
                        onLocaleSelected(locale)
                        expanded = false
                    }
                )
            }
        }
    }
}