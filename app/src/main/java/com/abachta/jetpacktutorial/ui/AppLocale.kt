package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider

sealed class AppLocale(
    val tag: String,
    val flag: String,
    @StringRes val displayNameResId: Int
) : SelectableTextProvider {

    data object Polish : AppLocale("pl", "\uD83C\uDDF5\uD83C\uDDF1", R.string.polish)
    data object English : AppLocale("en", "\uD83C\uDDEC\uD83C\uDDE7", R.string.english)

    @Composable
    override fun getText(): String = "$flag ${stringResource(displayNameResId)}"

    companion object {

        val entries by lazy {
            listOf(Polish, English)
        }

        fun fromLanguageTag(tag: String): AppLocale {
            return when (tag) {
                "pl" -> Polish
                else -> English
            }
        }
    }
}