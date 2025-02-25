package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.ShuffleOn
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.settings.AppLocale
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.CodeListingFont
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import com.abachta.jetpacktutorial.settings.LessonPopupOption
import com.abachta.jetpacktutorial.settings.QuizShufflingOption
import com.abachta.jetpacktutorial.ui.components.AttributionsDialog
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider
import com.abachta.jetpacktutorial.ui.components.SelectionDialog
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onClearLessons: () -> Unit,
    onChangePopupSettings: (LessonPopupOption) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // App language
        DialogRow(
            icon = Icons.Filled.Language,
            selectedItem = viewModel.locale,
            onItemSelected = { viewModel.locale = it },
            dialogItems = AppLocale.entries,
            dialogTitle = stringResource(R.string.select_app_language),
            rowTitle = stringResource(R.string.app_language)
        )

        val themeIcon = when (viewModel.theme) {
            AppTheme.Light -> Icons.Filled.LightMode
            AppTheme.Dark -> Icons.Filled.DarkMode
            AppTheme.Auto -> if (isSystemInDarkTheme()) Icons.Filled.DarkMode else Icons.Filled.LightMode
        }

        // App theme
        DialogRow(
            icon = themeIcon,
            selectedItem = viewModel.theme,
            onItemSelected = { viewModel.theme = it },
            dialogItems = AppTheme.entries,
            dialogTitle = stringResource(R.string.select_app_theme),
            rowTitle = stringResource(R.string.app_theme)
        )

        // Dynamic colors
        DialogRow(
            icon = Icons.Filled.ColorLens,
            selectedItem = viewModel.dynamicColors,
            onItemSelected = { viewModel.dynamicColors = it },
            dialogItems = DynamicColorsOption.entries,
            dialogTitle = stringResource(R.string.use_dynamic_colors),
            rowTitle = stringResource(R.string.dynamic_colors)
        )

        val popupIcon = when (viewModel.lessonPopup) {
            LessonPopupOption.Enabled -> Icons.Filled.Notifications
            LessonPopupOption.Disabled -> Icons.Filled.NotificationsNone
        }

        // Lesson popup
        DialogRow(
            icon = popupIcon,
            selectedItem = viewModel.lessonPopup,
            onItemSelected = {
                viewModel.lessonPopup = it
                onChangePopupSettings(it)
            },
            dialogItems = LessonPopupOption.entries,
            dialogTitle = stringResource(R.string.lesson_popup_options),
            rowTitle = stringResource(R.string.lesson_popup)
        )

        // Code font
        DialogRow(
            icon = Icons.Filled.Code,
            selectedItem = viewModel.listingFont,
            onItemSelected = {
                viewModel.listingFont = it
            },
            dialogItems = CodeListingFont.entries,
            dialogTitle = stringResource(R.string.select_listing_font),
            rowTitle = stringResource(R.string.code_listing_font)
        )

        val shuffleIcon = when (viewModel.questionShuffling) {
            QuizShufflingOption.NoShuffle -> Icons.Filled.Shuffle
            else -> Icons.Filled.ShuffleOn
        }

        // Quiz question shuffling
        DialogRow(
            icon = shuffleIcon,
            selectedItem = viewModel.questionShuffling,
            onItemSelected = {
                viewModel.questionShuffling = it
            },
            dialogItems = QuizShufflingOption.entries,
            dialogTitle = stringResource(R.string.select_quiz_shuffle),
            rowTitle = stringResource(R.string.quiz_shuffling_mode)
        )

        // Clear progress
        AlertDialogRow(
            rowIcon = Icons.Filled.DeleteForever,
            rowTitle = stringResource(R.string.clear_all_progress),
            dialogTitle = stringResource(R.string.dialog_clear_title),
            dialogIcon = Icons.Filled.DeleteForever,
            dialogText = stringResource(R.string.dialog_clear_text),
            onConfirm = onClearLessons
        )

        Spacer(modifier = Modifier.height(20.dp))

        AttributionsDialog()
    }
}

@Composable
fun <T : SelectableTextProvider> DialogRow(
    icon: ImageVector,
    rowTitle: String,
    dialogTitle: String,
    dialogItems: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    autoClose: Boolean = true
) {
    var dialogOpen by remember { mutableStateOf(false) }

    if (dialogOpen) {
        SelectionDialog(
            onDismissRequest = { dialogOpen = false },
            items = dialogItems,
            selectedItem = selectedItem,
            title = dialogTitle,
            onItemSelected = {
                if (autoClose) {
                    onItemSelected(it)
                    dialogOpen = false
                } else {
                    onItemSelected(it)
                }
            }
        )
    }

    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                dialogOpen = true
            }
            .padding(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = rowTitle,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun AlertDialogRow(
    rowIcon: ImageVector,
    rowTitle: String,
    dialogIcon: ImageVector,
    dialogTitle: String,
    dialogText: String,
    onConfirm: () -> Unit
) {
    var dialogOpen by remember { mutableStateOf(false) }
    val dangerColor = MaterialTheme.colorScheme.error

    if (dialogOpen) {
        AlertDialog(
            title = { Text(dialogTitle) },
            text = { Text(dialogText) },
            onDismissRequest = { dialogOpen = false },
            icon = {
                Icon(
                    imageVector = dialogIcon,
                    contentDescription = null,
                    tint = dangerColor
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogOpen = false
                    onConfirm()
                }) {
                    Text(
                        text = stringResource(R.string.dialog_clear_progress),
                        color = dangerColor
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogOpen = false
                }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }

    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                dialogOpen = true
            }
            .padding(8.dp),
    ) {
        Icon(
            imageVector = rowIcon,
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = rowTitle,
            modifier = Modifier.padding(8.dp)
        )
    }
}
