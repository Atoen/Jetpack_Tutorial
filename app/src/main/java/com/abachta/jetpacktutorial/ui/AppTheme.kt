package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R

enum class AppTheme(
    val value: Int,
    @StringRes val displayNameResId: Int
) {
    Auto(0, R.string.theme_auto),
    Light(1, R.string.theme_light),
    Dark(2, R.string.theme_dark);

    companion object {
        fun fromInt(int: Int): AppTheme {
            return when (int) {
                1 -> Light
                2 -> Dark
                else -> Auto
            }
        }
    }
}