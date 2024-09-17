package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R

enum class AppLocale(
    val tag: String,
    val flag: String,
    @StringRes val displayNameResId: Int
) {
    Polish("pl", "\uD83C\uDDF5\uD83C\uDDF1", R.string.polish),
    English("en", "\uD83C\uDDEC\uD83C\uDDE7", R.string.english);

    companion object {
        fun fromLanguageTag(tag: String): AppLocale {
            return when (tag) {
                "pl" -> Polish
                else -> English
            }
        }
    }
}