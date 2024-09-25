package com.abachta.jetpacktutorial.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider

sealed class LessonPopupOption(
    val enabled: Boolean,
    @StringRes val displayNameResId: Int
) : SelectableTextProvider {

    data object Enabled: LessonPopupOption(true, R.string.enabled)
    data object Disabled: LessonPopupOption(false, R.string.disabled)

    @Composable
    @ReadOnlyComposable
    override fun getText(): String = stringResource(displayNameResId)

    companion object {

        val entries by lazy {
            listOf(Enabled, Disabled)
        }

        fun fromBoolean(value: Boolean): LessonPopupOption {
            return when (value) {
                true -> Enabled
                false -> Disabled
            }
        }
    }
}