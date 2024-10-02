package com.abachta.jetpacktutorial.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider

sealed class CodeListingFont(
    val size: TextUnit,
    val lineHeight: TextUnit,
    val value: Int,
    @StringRes val displayNameResId: Int
) : SelectableTextProvider {

    data object Small : CodeListingFont(12.sp, 16.sp, 0, R.string.font_small)
    data object Medium : CodeListingFont(14.sp, 18.sp, 1, R.string.font_medium)
    data object Big : CodeListingFont(20.sp, 24.sp, 2, R.string.font_big)

    @Composable
    @ReadOnlyComposable
    override fun getText(): String = stringResource(displayNameResId)

    companion object {

        val entries by lazy {
            listOf(Small, Medium, Big)
        }

        fun fromInt(int: Int): CodeListingFont {
            return when (int) {
                0 -> Small
                1 -> Medium
                2 -> Big
                else -> Medium
            }
        }
    }
}

val LocalCodeListingFont = compositionLocalOf<CodeListingFont> {
    CodeListingFont.Medium
}