package com.abachta.jetpacktutorial.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider

sealed class AppTheme(
    val value: Int,
    @StringRes val displayNameResId: Int
) : SelectableTextProvider {

    data object Auto : AppTheme(0, R.string.theme_auto)
    data object Light : AppTheme(1, R.string.theme_light)
    data object Dark : AppTheme(2, R.string.theme_dark)

    @Composable
    @ReadOnlyComposable
    override fun getText(): String = stringResource(displayNameResId)

    companion object {

        val entries by lazy {
            listOf(Auto, Light, Dark)
        }

        fun fromInt(int: Int): AppTheme {
            return when (int) {
                0 -> Auto
                1 -> Light
                2 -> Dark
                else -> Auto
            }
        }
    }
}

val LocalAppTheme = compositionLocalOf<AppTheme> {
    AppTheme.Auto
}